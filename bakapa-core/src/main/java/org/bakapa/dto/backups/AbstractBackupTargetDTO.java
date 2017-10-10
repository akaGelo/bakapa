package org.bakapa.dto.backups;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.bakapa.domain.BackupTargetType;

import javax.validation.constraints.NotNull;
@AllArgsConstructor
public class AbstractBackupTargetDTO {

    @NonNull
    @NotNull
    @Getter
    private BackupTargetType targetType;
}