package ru.vyukov.bakapa.nativeutils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.exists;

/**
 * @author Oleg Vyukov
 */
public interface DumpUtilProcessBuilder<T extends DumpUtilProcessBuilder> {



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

    static public  Path findNative(String[] expectedPaths) {
        for (String path : expectedPaths) {
            Path mysqldump = Paths.get(path);
            if (exists(mysqldump)) {
                return mysqldump;
            }
        }
        return null;
    }
}
