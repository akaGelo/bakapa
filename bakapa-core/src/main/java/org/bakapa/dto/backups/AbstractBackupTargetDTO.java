package org.bakapa.dto.backups;

import lombok.*;
import org.bakapa.domain.BackupTargetType;
import org.bakapa.dto.agent.AgentDTO;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractBackupTargetDTO {


    @NonNull
    @NotNull
    private BackupTargetType targetType;


}