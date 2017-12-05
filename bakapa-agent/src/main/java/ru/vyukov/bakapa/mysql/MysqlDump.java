package ru.vyukov.bakapa.mysql;

import lombok.extern.slf4j.Slf4j;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupOptionsDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseLocationDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseUserCredentialsDTO;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Oleg Vyukov
 */
@Slf4j
public class MysqlDump implements Closeable {


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


    public InputStream dump() throws IOException {

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
        errorStreamLogging(process.getErrorStream());

        return process.getInputStream();
    }

    private void errorStreamLogging(InputStream errorStream) {
        new Thread(() -> {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
                String line = null;
                while (null != (line = bufferedReader.readLine())) {
                    //TODO send to log
                    log.error(line);
                }
            } catch (Exception e) {
                log.error("ErrorStream problem", e);
            }
        }).start();

    }


    public boolean isSuccess() {
        return 0 == process.exitValue();
    }

    @Override
    public void close() throws IOException {

    }
}