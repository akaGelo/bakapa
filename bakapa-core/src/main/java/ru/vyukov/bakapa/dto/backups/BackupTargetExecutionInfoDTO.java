package ru.vyukov.bakapa.dto.backups;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import ru.vyukov.bakapa.domain.BackupTargetStatus;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@AllArgsConstructor
public class BackupTargetExecutionInfoDTO {

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


    @JsonIgnore
    public boolean isError() {
        return BackupTargetStatus.ERROR.equals(lastStatus);
    }
}
