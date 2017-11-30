package ru.vyukov.bakapa.dto.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import ru.vyukov.bakapa.domain.AgentStatus;
import ru.vyukov.bakapa.dto.HealthDTO;

import javax.annotation.Nullable;
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

    @Nullable
    private HealthDTO health;

    @JsonIgnore
    public String getAgentId() {
        return agent.getAgentId();
    }
}
