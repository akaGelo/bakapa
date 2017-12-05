package ru.vyukov.bakapa.dump.mysql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import ru.vyukov.bakapa.nativeutils.DumpUtilProcessBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;
import java.nio.file.attribute.PosixFilePermission;
import java.util.*;

import static java.nio.file.Files.exists;
import static java.nio.file.attribute.PosixFilePermission.OWNER_EXECUTE;

/**
 * Priority on  native installation (/usr/bin/mysqldump). If native client not exist usage embedded mysqldump
 *
 * @author Oleg Vyukov
 */
@Slf4j
public class MysqlDumpProcessBuilder  implements DumpUtilProcessBuilder<MysqlDumpProcessBuilder> {

    private static final String[] EXPECTED_PATHS = new String[]{"/usr/bin/mysqldump", "/bin/mysqldump"};

    public static final String MYSQL_DUMP_APP_IMAGE = "MySqlDump.AppImage";


    /**
     * Cache extracted executable file
     */
    private static Path embeddedMysqlDumpExecutable;


    private final Path executable;

    private List<Object> args;


    static MysqlDumpProcessBuilder nativeBuilder() throws IOException {
        Path nativeMysqlDump = DumpUtilProcessBuilder.findNative(EXPECTED_PATHS);
        if (null == nativeMysqlDump) {
            throw new IOException(nativeMysqlDump + " not found");
        }
        return new MysqlDumpProcessBuilder(nativeMysqlDump);
    }


    static MysqlDumpProcessBuilder embeddedBuilder() throws IOException {
        checkDocker();
        Path mysqldump = extractEmbedded();
        return new MysqlDumpProcessBuilder(mysqldump);
    }

    private static Path nativePriorityBuilder() throws IOException {
        Path nativeMysqlDump = DumpUtilProcessBuilder.findNative(EXPECTED_PATHS);
        if (null != nativeMysqlDump) {
            return nativeMysqlDump;
        }
        checkDocker();
        return extractEmbedded();
    }

    /**
     * Native installed util priority
     *
     * @throws IOException
     */
    public MysqlDumpProcessBuilder() throws IOException {
        this(nativePriorityBuilder());
    }

    private MysqlDumpProcessBuilder(Path mysqldump) {
        this.executable = mysqldump;
        String fileName = mysqldump.toString();
        this.args = new ArrayList<>();
        this.args.add(fileName);
    }


    private static void checkDocker() throws IOException {
        if (!exists(Paths.get("/dev/fuse"))) {
            throw new IOException("Fuse module not available. Please install fuse or dump utils (mysqldump,pg_dump,etc)");
        }
    }

    private static Path extractEmbedded() throws IOException {
        if (null != embeddedMysqlDumpExecutable && Files.isExecutable(embeddedMysqlDumpExecutable)) {
            return embeddedMysqlDumpExecutable;
        }


        ClassPathResource classPathResource = new ClassPathResource(MYSQL_DUMP_APP_IMAGE);

        embeddedMysqlDumpExecutable = Files.createTempFile("mysqldump", "");

        try (InputStream inputStream = classPathResource.getInputStream();) {

            try (OutputStream fileOutputStream = Files.newOutputStream(embeddedMysqlDumpExecutable)) {
                FileCopyUtils.copy(inputStream, fileOutputStream);
            }
        } catch (Exception e) {
            log.error(MYSQL_DUMP_APP_IMAGE + " not available. Run 'maven compile' before this test");
            throw e;
        }

        Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(embeddedMysqlDumpExecutable);
        permissions.add(OWNER_EXECUTE);
        Files.setPosixFilePermissions(embeddedMysqlDumpExecutable, permissions);


        return embeddedMysqlDumpExecutable;
    }


    /**
     * Add argument
     *
     * @param arg
     * @return
     */
    @Override
    public MysqlDumpProcessBuilder arg(Object arg) {
        args.add(arg);
        return this;
    }

    @Override
    public Process build() throws IOException {
        String[] args = this.args.stream().map(Object::toString).toArray(String[]::new);

        log.debug("Build process " + Arrays.toString(args));

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(args);
        return processBuilder.start();
    }


    @Override
    public Path getExecutable() {
        return executable;
    }


}
