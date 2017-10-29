package org.bakapa.dto.agent;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bakapa.domain.AgentStatus;

import javax.validation.constraints.NotNull;

@Data
abstract public class AbstractAgentAndInfoDTO<AgentClass> {

    @NotNull
    @NonNull
    private AgentClass agent;

    @NotNull
    @NonNull
    private Integer backupsTargetsCount;

    @NotNull
    @NonNull
    private AgentStatus status;


}
