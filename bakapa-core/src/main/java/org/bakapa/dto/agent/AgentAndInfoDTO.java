package org.bakapa.dto.agent;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bakapa.domain.AgentStatus;

import javax.validation.constraints.NotNull;


@Builder
@Data
public class AgentAndInfoDTO {


    @NotNull
    @NonNull
    private AgentDTO agent;

    @NotNull
    @NonNull
    private Integer backupsTargetsCount;

    @NotNull
    @NonNull
    private AgentStatus status;


}
