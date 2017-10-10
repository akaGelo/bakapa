package org.bakapa.dto.backups;

import lombok.Builder;
import lombok.NonNull;
import org.bakapa.domain.BackupTargetType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class DirectoryBackupTargetDTO extends  AbstractBackupTargetDTO {

    @NonNull
    @NotNull
    @NotEmpty
    private String path;

    @Builder
    public DirectoryBackupTargetDTO(BackupTargetType targetType, String path) {
        super(targetType);
        this.path = path;
    }

}
