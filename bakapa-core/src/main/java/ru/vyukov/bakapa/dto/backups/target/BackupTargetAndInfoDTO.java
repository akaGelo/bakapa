package ru.vyukov.bakapa.dto.backups.target;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
public class BackupTargetAndInfoDTO {


    @NotNull
    @NonNull
    private SummaryBackupTargetDTO backupTarget;

    @NotNull
    @NonNull
    private BackupTargetExecutionInfoDTO executionInfo;


    public String getBackupTargetId() {
        return backupTarget.getBackupTargetId();
    }

    @JsonIgnore
    public String getNameReadable() {
        return backupTarget.getNameReadable();
    }
}
