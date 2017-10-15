package org.bakapa.dto.backups;

import lombok.*;
import org.bakapa.domain.BackupTargetType;
import org.bakapa.dto.agent.AgentDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractBackupTargetDTO {

    @NotEmpty
    private String abstractBackupTargetId;

    @NonNull
    @NotNull
    private BackupTargetType targetType;


}