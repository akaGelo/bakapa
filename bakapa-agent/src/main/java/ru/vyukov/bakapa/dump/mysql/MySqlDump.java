package ru.vyukov.bakapa.dump.mysql;

import lombok.extern.slf4j.Slf4j;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupTargetDTO;
import ru.vyukov.bakapa.dump.AbstractDatabaseDumpWrapperUtil;
import ru.vyukov.bakapa.dump.DumpUtilWrapper;
import ru.vyukov.bakapa.dump.ProcessDumpResult;

import java.io.*;

/**
 * @author Oleg Vyukov
 */
@Slf4j
public class MySqlDump extends AbstractDatabaseDumpWrapperUtil implements DumpUtilWrapper {


    private Process process;

    private File extractedFile;

    public MySqlDump(DatabaseBackupTargetDTO databaseBackupTarget) {
        super(databaseBackupTarget);
    }


    @Override
    public ProcessDumpResult dump() throws IOException {

        MysqlDumpProcessBuilder builder = new MysqlDumpProcessBuilder();

        builder.args("--host", location.getHost())
                .arg("--port").arg(location.getPort())
                .args("-u" + userCredentials.getUsername(), "-p" + userCredentials.getPassword())
                .args("--single-transaction", "--quick")
                .args("--routines", "--triggers", "--events");

        final String database = location.getDatabase();
        for (String tableName : options.getExcludeTables()) {
            builder.arg("--ignore-table=" + database + "." + tableName);
        }
        builder.arg(database);

        process = builder.build();

        return new ProcessDumpResult(process);
    }


    @Override
    public void close() throws IOException {
        if (null != process) {
            process.destroy();
        }
    }
}