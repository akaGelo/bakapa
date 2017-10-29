package org.bakapa.dto.agent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.bakapa.domain.AgentStatus;


public class AgentAndInfoDTO extends AbstractAgentAndInfoDTO<AgentDTO> {

    @Builder
    @JsonCreator
    public AgentAndInfoDTO(
            @JsonProperty("agent") AgentDTO agent,
            @JsonProperty("backupsTargetsCount") Integer backupsTargetsCount,
            @JsonProperty("status") AgentStatus status) {
        super(agent, backupsTargetsCount, status);
    }
}
