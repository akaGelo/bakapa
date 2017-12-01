package ru.vyukov.bakapa.fs;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.Sets;

import javax.annotation.Nullable;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;
import java.util.concurrent.Callable;

import static java.lang.Integer.MAX_VALUE;
import static java.nio.file.Files.walk;
import static java.nio.file.Files.walkFileTree;

/**
 * FOLLOW_LINKS
 *
 * @author Oleg Vyukov
 */
@Slf4j
public class DirectoryTarDump {

    private final Set<FileVisitOption> fileVisitOptions = Sets.newHashSet(FileVisitOption.FOLLOW_LINKS);


    private final Path target;

    private Thread thread;


    public DirectoryTarDump(Path target) {
        this.target = target;
    }

    public InputStream dump() throws IOException {
        singleExecutionCheck();

        final PipedInputStream in = new PipedInputStream();
        final PipedOutputStream out = new PipedOutputStream(in);

        runOnNewThread(() -> dump(out), onError(in));

        return in;
    }

    private Void dump(PipedOutputStream out) throws IOException {

        try (TarArchiveOutputStream outputStream = new TarArchiveOutputStream(out)) {
            walkAllFilesInFolder(outputStream);
        }

        return null;
    }

    /**
     * All problems -- close stream
     *
     * @param in
     * @return
     */
    private Runnable onError(PipedInputStream in) {
        return () -> {
            try {
                in.close();
            } catch (IOException e) {
                log.error("Dump inputStream close error");
            }
        };
    }

    private void singleExecutionCheck() {
        if (null != thread) {
            throw new IllegalStateException();
        }
    }

    /**
     * Recursive visit all files
     *
     * @param outputStream
     * @throws IOException
     */
    private void walkAllFilesInFolder(TarArchiveOutputStream outputStream) throws IOException {
        walkFileTree(target, fileVisitOptions, MAX_VALUE, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                try {
                    TarArchiveEntry archiveEntry = new TarArchiveEntry(path.toFile());
                    archiveEntry.setSize(Files.size(path));
                    outputStream.putArchiveEntry(archiveEntry);
                    Files.copy(path, outputStream);
                } finally {
                    outputStream.closeArchiveEntry();
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }


    private void runOnNewThread(Callable<Void> callable, Runnable onError) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    callable.call();
                } catch (Exception e) {
                    log.error("Backup error", e);
                    onError.run();
                }
            }
        }, "Dump " + target);
        thread.start();
    }


}
