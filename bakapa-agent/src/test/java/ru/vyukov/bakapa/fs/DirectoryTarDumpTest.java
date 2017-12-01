package ru.vyukov.bakapa.fs;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.NullOutputStream;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;

import static org.junit.Assert.*;

/**
 * @author Oleg Vyukov
 */
public class DirectoryTarDumpTest {


    @Rule
    public TemporaryFolder source = new TemporaryFolder();

    @Rule
    public TemporaryFolder verify = new TemporaryFolder();

    private File file1;
    private File file2;

    @Before
    public void setUp() throws Exception {
        File root = source.getRoot();
        file1 = new File(root, "1.txt");

        new File(root, "/2/").mkdirs();
        file2 = new File(root, "/2/2.txt");

        FileUtils.writeStringToFile(file1, "hello", Charset.defaultCharset());
        FileUtils.writeStringToFile(file2, "world", Charset.defaultCharset());
    }

    @Test
    public void dump() throws Exception {
        DirectoryTarDump directoryTarDump = new DirectoryTarDump(source.getRoot().toPath());
        InputStream dump = directoryTarDump.dump();

        File outFile = File.createTempFile("dump", ".tar");
        try (FileOutputStream output = new FileOutputStream(outFile)) {
            IOUtils.copy(dump, output);
        }
        assertTrue(outFile.length() > 0);


        File targetDir = extract(outFile);

        File extractedFile1 = new File(verify.getRoot() + file1.getAbsolutePath());
        File extractedFile2 = new File(verify.getRoot() + file2.getAbsolutePath());

        assertEqualsBody(file1, extractedFile1);
        assertEqualsBody(file2, extractedFile2);
    }


    private void assertEqualsBody(File fileA, File fileB) throws IOException {
        byte[] a = FileUtils.readFileToByteArray(fileA);
        byte[] b = FileUtils.readFileToByteArray(fileB);
        assertArrayEquals("Files not equals", a, b);
    }


    private File extract(File outFile) throws IOException {
        Archiver archiver = ArchiverFactory.createArchiver(outFile);
        archiver.extract(outFile, verify.getRoot());
        return verify.getRoot();
    }


    @Test(expected = IOException.class)
    public void dumpIOException() throws Exception {
        DirectoryTarDump directoryTarDump = new DirectoryTarDump(Paths.get("/etc/"));
        InputStream dump = directoryTarDump.dump();
        IOUtils.copy(dump, new NullOutputStream());
    }
}