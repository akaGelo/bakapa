package ru.vyukov.bakapa.dump.fs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import ru.vyukov.bakapa.dto.backups.target.impl.FilesystemBackupTargetDTO;
import ru.vyukov.bakapa.dump.DumpResult;
import ru.vyukov.bakapa.dump.DumpUtilWrapper;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;
import java.util.concurrent.Callable;

import static java.lang.Integer.MAX_VALUE;
import static java.nio.file.Files.walkFileTree;
import static java.util.Collections.unmodifiableSet;
import static org.apache.commons.compress.utils.Sets.newHashSet;

/**
 * Dump directory of single file  to Tar
 * FOLLOW_LINKS
 *
 * @author Oleg Vyukov
 */
@Slf4j
public class DirectoryTarDump implements DumpUtilWrapper {

    private final Set<FileVisitOption> fileVisitOptions = unmodifiableSet(newHashSet(FileVisitOption.FOLLOW_LINKS));

    private final Path target;

    private Thread thread;


    DirectoryTarDump(File target) {
        this.target = target.toPath();
    }

    public DirectoryTarDump(FilesystemBackupTargetDTO filesystemBackupTarget) {
        this.target = Paths.get(filesystemBackupTarget.getPath());
    }

    @Override
    public DumpResult dump() throws IOException {
        singleExecutionCheck();

        final PipedInputStream in = new PipedInputStream();
        final PipedOutputStream out = new PipedOutputStream(in);

        runOnNewThread(() -> dump(out), onError(in));

        return new FsDumpResult(in, thread);
    }

    private Void dump(PipedOutputStream out) throws IOException {

        try (TarArchiveOutputStream outputStream = new TarArchiveOutputStream(out)) {
            walkAllFilesInFolderOrSingFile(outputStream);
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
    private void walkAllFilesInFolderOrSingFile(TarArchiveOutputStream outputStream) throws IOException {

        SimpleFileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
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
        };

        if (Files.isDirectory(target)) {
            walkFileTree(target, fileVisitOptions, MAX_VALUE, visitor);
        } else {
            visitor.visitFile(target, null);
        }
    }


    private void runOnNewThread(Callable<Void> dump, Runnable onError) {
        thread = new Thread(() -> {
            try {
                dump.call();
            } catch (Exception e) {
                log.error("Backup error", e);
                onError.run();
            }
        }, "Dump " + target);
        thread.start();
    }


    @Override
    public void close() throws IOException {

    }
}
