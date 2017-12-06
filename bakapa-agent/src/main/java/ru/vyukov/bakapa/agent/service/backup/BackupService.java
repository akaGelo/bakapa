package ru.vyukov.bakapa.agent.service.backup;

import ru.vyukov.bakapa.dto.backups.task.BackupTaskDTO; /**
 * @author Oleg Vyukov
 */
public interface  BackupService {

    void execute(BackupTaskDTO backupTask);
}
