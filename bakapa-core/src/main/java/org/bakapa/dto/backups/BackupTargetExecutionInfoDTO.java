package org.bakapa.dto.backups;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bakapa.domain.BackupTargetStatus;

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
