package ru.vyukov.bakapa.dump.fs;

import lombok.Value;
import org.apache.commons.io.input.NullInputStream;
import ru.vyukov.bakapa.dump.DumpResult;

import java.io.InputStream;
import java.io.PipedInputStream;

/**
 * @author Oleg Vyukov
 */
@Value
public class FsDumpResult implements DumpResult {
    private final InputStream inputStream;

    private final Thread thread;


    @Override
    public InputStream getErrorStream() {
        return new NullInputStream(0);
    }



    @Override
    public boolean isSuccess() throws IllegalThreadStateException {
        return !thread.isAlive();
    }
}
