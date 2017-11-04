package ru.vyukov.bakapa.dto.backups;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
public class BackupTargetAndInfoDTO {


    @NotNull
    @NonNull
    private AbstractBackupTargetDTO backupTarget;

    @NotNull
    @NonNull
    private BackupTargetExecutionInfoDTO executionInfo;

}
