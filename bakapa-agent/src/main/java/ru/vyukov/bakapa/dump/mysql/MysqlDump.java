package ru.vyukov.bakapa.dump.mysql;

import lombok.extern.slf4j.Slf4j;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupOptionsDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseLocationDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseUserCredentialsDTO;
import ru.vyukov.bakapa.dump.ProcessDumpResult;

import java.io.*;

/**
 * @author Oleg Vyukov
 */
@Slf4j
public class MysqlDump implements DumpUtilWrapper {


    private final DatabaseUserCredentialsDTO userCredentials;
    private final DatabaseLocationDTO location;
    private final DatabaseBackupOptionsDTO options;

    private Process process;

    private File extractedFile;

    public MysqlDump(DatabaseBackupTargetDTO databaseBackupTarget) throws IOException {
        userCredentials = databaseBackupTarget.getUserCredentials();
        location = databaseBackupTarget.getLocation();
        options = databaseBackupTarget.getOptions();
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