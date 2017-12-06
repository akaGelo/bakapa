package ru.vyukov.bakapa.dto.backups.task;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import ru.vyukov.bakapa.dto.backups.target.BackupTarget;
import ru.vyukov.bakapa.dto.backups.target.SummaryBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.FilesystemBackupTargetDTO;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(of = "backupId")
public class BackupTaskDTO {

    public static final String DESTINATION = "/backupTask";

    @NotNull
    @NonNull
    private String backupId;


    @NotNull
    @NonNull
    private SummaryBackupTargetDTO backupTarget;

    public static BackupTaskDTO demo() {
        return new BackupTaskDTO("testBackupId", FilesystemBackupTargetDTO.defaultExample());
    }
}
