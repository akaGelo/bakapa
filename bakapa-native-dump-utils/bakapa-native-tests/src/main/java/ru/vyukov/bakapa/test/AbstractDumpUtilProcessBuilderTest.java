package ru.vyukov.bakapa.test;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import ru.vyukov.bakapa.nativeutils.DumpUtilPrecessBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * @author Oleg Vyukov
 */
public abstract class AbstractDumpUtilProcessBuilderTest {


    @Test
    public void cacheExecutableFile() throws IOException {
        DumpUtilPrecessBuilder<?> process1 = getEmbeddedProcessBuilder();
        DumpUtilPrecessBuilder<?> process2 = getEmbeddedProcessBuilder();


        Path p1Str = process1.getExecutable();
        Path p2Str = process2.getExecutable();
        assertEquals(p1Str, p2Str);
    }


    @Test(timeout = 10_000)
    public void version() throws Exception {
        DumpUtilPrecessBuilder<?> mysqlDumpProcessBuilder = getEmbeddedProcessBuilder();

        ExecutorService executorService = Executors.newCachedThreadPool();

        Process process = mysqlDumpProcessBuilder.arg(getVersionArg()).build();

        InputStream errorStream = process.getErrorStream();
        InputStream inputStream = process.getInputStream();

        Future<String> errorFuture = executorService.submit(() -> IOUtils.toString(errorStream, Charset.defaultCharset()));
        Future<String> inputFuture = executorService.submit(() -> IOUtils.toString(inputStream, Charset.defaultCharset()));


        //example mysqldump Ver 10.13 Distrib 5.5 .55,for debian - linux - gnu(x86_64)
        assertThat(inputFuture.get(), containsString(getAssertThatVersion()));
        assertTrue(errorFuture.get().isEmpty());

        process.waitFor(3, SECONDS);
        assertEquals(0, process.exitValue());
    }


    abstract protected DumpUtilPrecessBuilder<?> getEmbeddedProcessBuilder() throws IOException;


    abstract public String getAssertThatVersion();

    abstract public String getVersionArg();

}
