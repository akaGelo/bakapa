package ru.vyukov.bakapa.controller.controller.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import org.bakapa.domain.AgentStatus;
import org.bakapa.dto.agent.AbstractAgentAndInfoDTO;
import org.bakapa.dto.agent.AgentDTO;
import ru.vyukov.bakapa.controller.domain.View;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.agent.Agent;

/**
 * @see BackupTargetAndInfo  refactoring
 */
public class AgentAndInfo extends AbstractAgentAndInfoDTO<Agent> {

    @Builder
    private AgentAndInfo(Agent agent, Integer backupsTargetsCount, AgentStatus status) {
        super(agent, backupsTargetsCount, status);
    }

    @Override
    @JsonView(Summary.class)
    public Agent getAgent() {
        return super.getAgent();
    }

    @Override
    @JsonView(Summary.class)
    public Integer getBackupsTargetsCount() {
        return super.getBackupsTargetsCount();
    }

    @Override
    @JsonView(Summary.class)
    public AgentStatus getStatus() {
        return super.getStatus();
    }
}
