package org.bakapa.dto.backups;

import lombok.*;
import org.bakapa.domain.BackupTargetType;
import org.bakapa.dto.agent.AgentDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class DirectoryBackupTargetDTO extends AbstractBackupTargetDTO {

    @NonNull
    @NotNull
    @NotEmpty
    private String path;


    @Builder
    public DirectoryBackupTargetDTO(String backupTargetId, AgentDTO agent, String path) {
        super(backupTargetId, BackupTargetType.FILESYSTEM);
        this.path = path;
    }

}
