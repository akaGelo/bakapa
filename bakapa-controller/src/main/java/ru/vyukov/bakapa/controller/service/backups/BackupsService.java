package ru.vyukov.bakapa.controller.service.backups;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.Backup;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;

public interface BackupsService {
    Backup createNewBackup(AbstractBackupTarget backupTarget) throws CreateBackupException;

    Page<Backup> getBackups(Pageable pageable);

    Page<Backup> getBackups(AbstractBackupTarget backupTarget, Pageable pageable);

    Page<Backup> getBackups(Agent agent, Pageable pageable);
}
