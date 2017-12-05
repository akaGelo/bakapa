package ru.vyukov.bakapa.nativeutils;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.exists;

/**
 * @author Oleg Vyukov
 */
abstract public class AbstractDumpUtil {


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
