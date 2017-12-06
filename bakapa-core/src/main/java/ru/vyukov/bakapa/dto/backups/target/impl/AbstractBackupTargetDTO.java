package ru.vyukov.bakapa.dto.backups.target.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.validators.CronExpression;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"backupTargetId", "targetType"})
abstract class AbstractBackupTargetDTO {


    protected String backupTargetId;


    @NonNull
    @NotNull
    protected BackupTargetType targetType;

    @NonNull
    @NotNull
    @CronExpression
    protected String trigger;


    @JsonIgnore
    public boolean isDatabaseType() {
        return targetType.isDatabase();
    }


    @JsonIgnore
    public boolean isFilesystemType() {
        return targetType.isFilesystem();
    }

    public static AbstractBackupTargetDTO demo(String backupTargetId) {
        return FilesystemBackupTargetDTO.demo(backupTargetId);
    }
}