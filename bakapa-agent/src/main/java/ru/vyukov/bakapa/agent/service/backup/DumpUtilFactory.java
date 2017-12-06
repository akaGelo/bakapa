package ru.vyukov.bakapa.agent.service.backup;

import ru.vyukov.bakapa.dto.backups.task.BackupTaskDTO;
import ru.vyukov.bakapa.dump.DumpUtilWrapper;

/**
 * @author Oleg Vyukov
 */
interface DumpUtilFactory {
    DumpUtilWrapper create(BackupTaskDTO backupTask);
}
