package ru.vyukov.bakapa.agent.service.backup;

import ru.vyukov.bakapa.dto.backups.task.BackupTaskDTO;

/**
 * @author Oleg Vyukov
 */
interface Logger {
    void logError(BackupTaskDTO backupTask, String message, Exception e);

    void logInfo(BackupTaskDTO backupTask, String message);
}
