package ru.vyukov.bakapa.dto.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import ru.vyukov.bakapa.domain.AgentStatus;

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

    @JsonIgnore
    public String getAgentId() {
        return agent.getAgentId();
    }
}
