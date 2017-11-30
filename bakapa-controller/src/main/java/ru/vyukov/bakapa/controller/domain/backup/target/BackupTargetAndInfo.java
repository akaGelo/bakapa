package ru.vyukov.bakapa.controller.domain.backup.target;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.NonNull;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.domain.backup.target.BackupTargetExecutionInfo;

import javax.validation.constraints.NotNull;

@Builder
public class BackupTargetAndInfo {


    @NotNull
    @NonNull
    @JsonView(Summary.class)
    private AbstractBackupTarget backupTarget;

    @NotNull
    @NonNull
    @JsonView(Summary.class)
    private BackupTargetExecutionInfo executionInfo;



}
