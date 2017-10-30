package ru.vyukov.bakapa.controller.controller.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.NonNull;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.domain.backup.BackupTargetExecutionInfo;

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
