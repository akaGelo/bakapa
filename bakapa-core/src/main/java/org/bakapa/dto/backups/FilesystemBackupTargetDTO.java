package org.bakapa.dto.backups;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;
import org.bakapa.domain.BackupTargetType;
import org.bakapa.dto.agent.AgentDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;

@Data
@NoArgsConstructor
public class FilesystemBackupTargetDTO extends AbstractBackupTargetDTO {

    @NonNull
    @NotNull
    @NotEmpty
    private String path;


    @Builder
    @JsonCreator
    @ConstructorProperties({"backupTargetId", "agent", "path"})
    public FilesystemBackupTargetDTO(String backupTargetId, AgentDTO agent, String path) {
        super(backupTargetId, BackupTargetType.FILESYSTEM);
        this.path = path;
    }

}
