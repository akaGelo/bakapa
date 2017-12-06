package ru.vyukov.bakapa.dump.postgresql;

import lombok.extern.slf4j.Slf4j;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupTargetDTO;
import ru.vyukov.bakapa.dump.AbstractDatabaseDumpWrapperUtil;
import ru.vyukov.bakapa.dump.DumpResult;
import ru.vyukov.bakapa.dump.DumpUtilWrapper;

import java.io.IOException;

/**
 * @author Oleg Vyukov
 */
@Slf4j
public class PostgreSqlDump extends AbstractDatabaseDumpWrapperUtil implements DumpUtilWrapper {


    public PostgreSqlDump(DatabaseBackupTargetDTO databaseBackupTarget) {
        super(databaseBackupTarget);
    }

    @Override
    public DumpResult dump() throws IOException {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public void close() throws IOException {
        throw new IllegalStateException("not implemented");
    }
}