package ru.vyukov.bakapa.controller.controller.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.NonNull;
import org.bakapa.dto.backups.AbstractBackupTargetAndInfoDTO;
import org.bakapa.dto.backups.BackupTargetExecutionInfoDTO;
import ru.vyukov.bakapa.controller.domain.View;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.domain.backup.BackupTargetExecutionInfo;

import javax.validation.constraints.NotNull;

public class BackupTargetAndInfo {


    @NotNull
    @NonNull
    @JsonView(Summary.class)
    private AbstractBackupTarget backupTarget;

    @NotNull
    @NonNull
    @JsonView(Summary.class)
    private BackupTargetExecutionInfo executionInfo;


    @Builder
    public BackupTargetAndInfo(AbstractBackupTarget backupTarget, BackupTargetExecutionInfo executionInfo) {
        this.backupTarget = backupTarget;
        this.executionInfo = executionInfo;
    }
}
