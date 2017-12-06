package ru.vyukov.bakapa.agent.service.backup;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import ru.vyukov.bakapa.dto.backups.task.BackupTaskDTO;
import ru.vyukov.bakapa.dto.backups.task.BackupTaskFinishEventDTO;
import ru.vyukov.bakapa.dto.backups.task.BackupTaskLogEventDTO;

import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.vyukov.bakapa.dto.backups.task.BackupTaskLogEventDTO.error;
import static ru.vyukov.bakapa.dto.backups.task.BackupTaskLogEventDTO.info;

/**
 * @author Oleg Vyukov
 */
@Service
public class BackupServiceImpl implements BackupService, Logger {

    private final BackupServiceConfig backupServiceConfig;

    private final ExecutorService executorService;

    private final SimpMessageSendingOperations messageSendingOperations;

    private final BackupScriptFactory backupScriptFactory;

    @Autowired
    public BackupServiceImpl(BackupServiceConfig backupServiceConfig, SimpMessageSendingOperations messageSendingOperations, BackupScriptFactory backupScriptFactory) {
        this(backupServiceConfig, messageSendingOperations, Executors.newSingleThreadExecutor(), backupScriptFactory);
    }

    BackupServiceImpl(BackupServiceConfig backupServiceConfig, SimpMessageSendingOperations messageSendingOperations, ExecutorService executorService, BackupScriptFactory backupScriptFactory) {
        this.backupServiceConfig = backupServiceConfig;
        this.executorService = executorService;
        this.messageSendingOperations = messageSendingOperations;
        this.backupScriptFactory = backupScriptFactory;
    }

    @PreDestroy
    public void shutdown() {
        executorService.shutdown();
    }

    @Override
    public void execute(BackupTaskDTO backupTask) {
        //this method not use @Async, because single thread = implementation details
        logInfo(backupTask, "Add backup task to queue");
        executorService.execute(() -> backup(backupTask));
    }

    void backup(BackupTaskDTO backupTask) {
        try {
            logInfo(backupTask, "Start backup");


            BackupScript backupScript = backupScriptFactory.create(backupTask, backupServiceConfig, this);

            backupScript.run();

            messageSendingOperations.convertAndSend(BackupTaskFinishEventDTO.DESTINATION, BackupTaskFinishEventDTO.success(backupTask));
        } catch (Exception e) {
            logError(backupTask, "Problem", e);
            messageSendingOperations.convertAndSend(BackupTaskFinishEventDTO.DESTINATION, BackupTaskFinishEventDTO.fail(backupTask));
        } finally {
            logInfo(backupTask, "Finish backup task");
        }
    }

    @Override
    public void logError(BackupTaskDTO backupTask, String message, Exception e) {
        String stackTrace = ExceptionUtils.getStackTrace(e);
        message += "\n" + stackTrace;
        messageSendingOperations.convertAndSend(BackupTaskLogEventDTO.DESTINATION, error(backupTask, message));
    }

    @Override
    public void logInfo(BackupTaskDTO backupTask, String message) {
        messageSendingOperations.convertAndSend(BackupTaskLogEventDTO.DESTINATION, info(backupTask, message));
    }
}
