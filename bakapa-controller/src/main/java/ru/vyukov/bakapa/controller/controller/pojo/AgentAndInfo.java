package ru.vyukov.bakapa.controller.controller.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.NonNull;
import org.bakapa.domain.AgentStatus;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.agent.Agent;

import javax.validation.constraints.NotNull;

/**
 * @see BackupTargetAndInfo  refactoring
 */
@Builder
public class AgentAndInfo {


    @NotNull
    @NonNull
    @JsonView(Summary.class)
    private Agent agent;

    @NotNull
    @NonNull
    @JsonView(Summary.class)
    private Integer backupsTargetsCount;

    @NotNull
    @NonNull
    @JsonView(Summary.class)
    private AgentStatus status;


}
