package ru.vyukov.bakapa.dump;

import java.io.InputStream;

/**
 * @author Oleg Vyukov
 */
public interface DumpResult {
    InputStream getInputStream();

    InputStream getErrorStream();

    boolean isSuccess() throws IllegalThreadStateException;
}
