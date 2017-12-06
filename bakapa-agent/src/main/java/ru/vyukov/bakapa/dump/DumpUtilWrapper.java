package ru.vyukov.bakapa.dump;

import ru.vyukov.bakapa.dump.DumpResult;
import ru.vyukov.bakapa.dump.ProcessDumpResult;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Oleg Vyukov
 */
public interface DumpUtilWrapper extends Closeable {

    DumpResult dump() throws IOException;
}
