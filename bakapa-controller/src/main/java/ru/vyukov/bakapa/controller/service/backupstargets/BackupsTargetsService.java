package ru.vyukov.bakapa.controller.service.backupstargets;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vyukov.bakapa.controller.domain.agent.AgentAndInfo;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;

import java.util.List;

public interface BackupsTargetsService {
    /**
     * Return all backups targets on all agents
     *
     * @return
     */
    List<AbstractBackupTarget> getBackupsTargets();

    Page<AbstractBackupTarget> getBackupsTargets(Pageable pageable);

    List<AbstractBackupTarget> getBackupsTargets(Agent agent);

    AbstractBackupTarget getBackupTarget(String backupTargetId) throws BackupTargetNotFoundException;

    AbstractBackupTarget updateBackupTarget(AbstractBackupTarget backupTarget);

    AbstractBackupTarget getBackupTarget(Agent agent, String backupTargetId) throws BackupTargetNotFoundException;

    void setInfo(AgentAndInfo.AgentAndInfoBuilder agentAndInfoBuilder);
}
