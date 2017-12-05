package ru.vyukov.bakapa.nativeutils;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author Oleg Vyukov
 */
public interface DumpUtilPrecessBuilder<T extends DumpUtilPrecessBuilder> {

    T arg(Object arg);

    default T args(String... args) {
        for (Object arg : args) {
            arg(arg);
        }
        return (T) this;
    }

    Process build() throws IOException;

    /**
     * @return path to executable util file
     */
    Path getExecutable();
}
