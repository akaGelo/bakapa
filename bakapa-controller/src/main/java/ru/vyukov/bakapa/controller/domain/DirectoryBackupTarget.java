package ru.vyukov.bakapa.controller.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import org.bakapa.domain.BackupTargetType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Target directory
 */
@Getter
public class DirectoryBackupTarget extends AbstractBackupTarget {


    @NonNull
    @NotNull
    @NotEmpty
    private String path;

    @Builder
    public DirectoryBackupTarget(BackupTargetType targetType, String path) {
        super(targetType);
        this.path = path;
    }
}
