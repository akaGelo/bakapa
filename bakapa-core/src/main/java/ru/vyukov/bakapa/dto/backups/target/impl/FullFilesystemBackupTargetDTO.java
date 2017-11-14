package ru.vyukov.bakapa.dto.backups.target.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vyukov.bakapa.dto.agent.AgentDTO;
import ru.vyukov.bakapa.dto.backups.target.FullBackupTargetDTO;

@NoArgsConstructor
public class FullFilesystemBackupTargetDTO extends FilesystemBackupTargetDTO implements FullBackupTargetDTO {

    @Getter
    @Setter
    private AgentDTO agent;

    private FullFilesystemBackupTargetDTO(String backupTargetId, String trigger, String path, AgentDTO agent) {
        super(backupTargetId, trigger, path);
        this.agent = agent;
    }


    public static FullFilesystemBackupTargetDTO defaultExample() {
        return new FullFilesystemBackupTargetDTO("testBackupTarget", EVERY_DAY_CRON_TRIGGER, "/etc/", AgentDTO.demo("testAgent1"));
    }

}
