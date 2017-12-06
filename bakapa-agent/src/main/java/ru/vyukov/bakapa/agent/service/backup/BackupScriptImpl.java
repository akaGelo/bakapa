package ru.vyukov.bakapa.agent.service.backup;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vyukov.bakapa.dto.backups.task.BackupTaskDTO;
import ru.vyukov.bakapa.dump.DumpUtilWrapper;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author Oleg Vyukov
 */
@Builder
@AllArgsConstructor(access = PRIVATE)
public class BackupScriptImpl implements BackupScript {

    private BackupTaskDTO backupTask;

    @NonNull
    private final Logger logger;

    @NonNull
    private  BackupServiceConfig backupServiceConfig;

    @NonNull
    private DumpUtilWrapper dumpUtilWrapper;


    @Override
    public void run() throws Exception {
        logInfo("Start");



    }

    private void logInfo(String message) {
        logger.logInfo(backupTask, message);
    }


    private void logError(String message) {
        logger.logInfo(backupTask, message);
    }
}
