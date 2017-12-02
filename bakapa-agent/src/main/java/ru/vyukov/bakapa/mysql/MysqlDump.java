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
        init();
    }


    /**
     * Extract native dump util and
     */
    private void init() throws IOException {
//        ClassPathResource utilDistributive = new ClassPathResource("/native/mysql/mysqldump");
//        extractedFile = File.createTempFile("mysqldump", "");
//        try (InputStream source = utilDistributive.getInputStream()) {
//            FileUtils.copyToFile(source, extractedFile);
//        }
//        extractedFile.setExecutable(true);

        //TODO check mysqldump installed
    }

    public InputStream dump() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("/usr/bin/mysqldump");
        List<String> command = processBuilder.command();
        command.addAll(Arrays.asList(
                "--host", location.getHost(), "--port", location.getPort() + "",
                "-u" + userCredentials.getUsername(), "-p" + userCredentials.getPassword(),
                "--single-transaction", "--quick",
                "--routines", "--triggers", "--events"
        ));

        final String database = location.getDatabase();

        for (String tableName : options.getExcludeTables()) {
            command.add("--ignore-table=" + database + "." + tableName);
        }
        command.add(database);
        process = processBuilder.start();
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