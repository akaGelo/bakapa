package ru.vyukov.bakapa.dump.fs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.output.NullOutputStream;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.vyukov.bakapa.dump.DumpResult;

import java.io.*;
import java.time.Duration;
import java.time.Instant;

import static com.jakewharton.byteunits.BinaryByteUnit.GIBIBYTES;
import static com.jakewharton.byteunits.BinaryByteUnit.MEBIBYTES;
import static java.time.Instant.now;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Oleg Vyukov
 */
@Slf4j
@Ignore("only debug")
public class DirectoryTarDumpSpeedTest {


    @Rule
    public TemporaryFolder source = new TemporaryFolder();
    public File file;

    @Before
    public void setUp() throws Exception {
        file = new File(source.getRoot(), "testFile");
    }

    @Test
    public void test500M() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.setLength(MEBIBYTES.toBytes(500));
        test();
    }

    @Test
    public void test1G() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.setLength(GIBIBYTES.toBytes(1));
        test();
    }


    public void test() throws IOException {
        Instant start = now();

        DirectoryTarDump directoryTarDump = new DirectoryTarDump(file);
        DumpResult dump = directoryTarDump.dump();
        IOUtils.copy(dump.getInputStream(), new NullOutputStream());


        Duration duration = Duration.between(start, now());

        log.info("Duration " + duration.getSeconds());
    }
}