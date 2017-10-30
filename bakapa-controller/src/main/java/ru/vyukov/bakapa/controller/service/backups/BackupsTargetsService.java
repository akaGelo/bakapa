package ru.vyukov.bakapa.controller.service.backups;

import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;

import java.util.List;

public interface BackupsTargetsService {
    /**
     * Return all backups targets on all agents
     *
     * @return
     */
    List<AbstractBackupTarget> getBackupsTargets();

    List<AbstractBackupTarget> getBackupsTargets(Agent agent);

    int getBackupsTargetsCount(Agent agent);

    AbstractBackupTarget getBackupTarget(String backupTargetId) throws BackupTargetNotFoundException;

    AbstractBackupTarget updateBackupTarget(AbstractBackupTarget backupTarget);
}
