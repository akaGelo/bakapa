package ru.vyukov.bakapa.dump;

import lombok.Value;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author Oleg Vyukov
 */
@Value
public class ProcessDumpResult implements DumpResult {

    private Process process;


    @Override
    public InputStream getInputStream() {
        return process.getInputStream();
    }

    @Override
    public InputStream getErrorStream() {
        return process.getErrorStream();
    }

    public boolean waitFor(long timeout, TimeUnit unit) throws InterruptedException {
        return process.waitFor(timeout, unit);
    }

    /**
     * @return
     * @throws IllegalThreadStateException "process hasn't exited"
     */
    @Override
    public boolean isSuccess() throws IllegalThreadStateException {
        return 0 == process.exitValue();
    }
}
