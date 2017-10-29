package org.bakapa.dto.backups;


import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
abstract public class AbstractBackTargetAndInfoDTO<ABT> {

    @NotNull
    @NonNull
    private ABT backupTarget;

    @NotNull
    @NonNull
    private BackupTargetExecutionInfo executionInfo;


}
