package ru.vyukov.bakapa.admin.service.agents.backups;

import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.dto.agent.AgentDTO;
import ru.vyukov.bakapa.dto.backups.AbstractBackupTargetDTO;


public interface BackupTargetsFactory {
    AbstractBackupTargetDTO newInstance(AgentDTO agent, BackupTargetType type);
}
