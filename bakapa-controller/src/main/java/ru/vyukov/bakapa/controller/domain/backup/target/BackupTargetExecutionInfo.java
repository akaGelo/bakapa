package ru.vyukov.bakapa.controller.domain.backup.target;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import ru.vyukov.bakapa.domain.BackupTargetStatus;
import ru.vyukov.bakapa.controller.domain.View.Summary;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Builder
@AllArgsConstructor
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
