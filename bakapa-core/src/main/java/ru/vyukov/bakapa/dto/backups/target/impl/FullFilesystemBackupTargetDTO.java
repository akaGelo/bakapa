package ru.vyukov.bakapa.dto.backups.target.impl;

import lombok.Getter;
import lombok.Setter;
import ru.vyukov.bakapa.dto.agent.AgentDTO;
import ru.vyukov.bakapa.dto.backups.target.FullBackupTargetDTO;

public class FullFilesystemBackupTargetDTO extends FilesystemBackupTargetDTO implements FullBackupTargetDTO {

    @Getter
    @Setter
    private AgentDTO agent;

}
