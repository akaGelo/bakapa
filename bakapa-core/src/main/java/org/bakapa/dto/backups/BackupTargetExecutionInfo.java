package org.bakapa.dto.backups;

import lombok.Data;
import lombok.NonNull;
import org.bakapa.domain.BackupTargetStatus;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
public class BackupTargetExecutionInfo {

    @NonNull
    @NotNull
    private Integer lastSizeBytes;

    @NonNull
    @NotNull
    private Instant lastExecutionTimestamp;

    @NonNull
    @NotNull
    private BackupTargetStatus lastStatus;

    @NonNull
    @NotNull
    private Instant nextExecutionTimestamp;
}
