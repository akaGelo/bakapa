package ru.vyukov.bakapa.admin.service.agents.backups;

import org.bakapa.domain.BackupTargetType;
import org.bakapa.dto.agent.AgentDTO;
import org.bakapa.dto.backups.AbstractBackupTargetDTO;


public interface BackupTargetsFactory {
    AbstractBackupTargetDTO newInstance(AgentDTO agent, BackupTargetType type);
}
