package ru.vyukov.bakapa.agent.service.backup;

import ru.vyukov.bakapa.dto.backups.task.BackupTaskDTO;

/**
 * @author Oleg Vyukov
 */
interface BackupScriptFactory {

    BackupScript create(BackupTaskDTO backupTaskDTO, BackupServiceConfig backupServiceConfig, Logger logger);
}
