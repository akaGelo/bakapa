package ru.vyukov.bakapa.controller.domain.backup;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.NonNull;
import org.bakapa.domain.BackupTargetStatus;
import ru.vyukov.bakapa.controller.domain.View;
import ru.vyukov.bakapa.controller.domain.View.Summary;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Builder(builderMethodName = "executionInfo")
public class BackupTargetExecutionInfo {

    @NonNull
    @NotNull
    @JsonView(Summary.class)
    private Integer lastSizeBytes;

    @NonNull
    @NotNull
    @JsonView(Summary.class)
    private Instant lastExecutionTimestamp;

    @NonNull
    @NotNull
    @JsonView(Summary.class)
    private BackupTargetStatus lastStatus;

    @NonNull
    @NotNull
    @JsonView(Summary.class)
    private Instant nextExecutionTimestamp;
}
