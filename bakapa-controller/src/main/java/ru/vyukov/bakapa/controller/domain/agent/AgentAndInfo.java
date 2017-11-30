package ru.vyukov.bakapa.controller.domain.agent;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import ru.vyukov.bakapa.controller.domain.backup.target.BackupTargetAndInfo;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.domain.AgentStatus;
import ru.vyukov.bakapa.dto.HealthDTO;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * @see BackupTargetAndInfo  refactoring
 */
@Value
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

    @JsonView(Summary.class)
    @Nullable
    private Health health;


    public static AgentAndInfoBuilder builder(Agent agent) {
        return new AgentAndInfoBuilder(agent);
    }

    @ToString
    public static class AgentAndInfoBuilder {
        @Getter
        private final Agent agent;

        private Integer backupsTargetsCount;
        private AgentStatus status;
        private Health health;

        AgentAndInfoBuilder(Agent agent) {
            this.agent = agent;
        }


        public AgentAndInfoBuilder backupsTargetsCount(Integer backupsTargetsCount) {
            this.backupsTargetsCount = backupsTargetsCount;
            return this;
        }

        public AgentAndInfoBuilder status(AgentStatus status) {
            this.status = status;
            return this;
        }

        public AgentAndInfoBuilder health(Health health) {
            this.health = health;
            return this;
        }

        public AgentAndInfo build() {
            return new AgentAndInfo(agent, backupsTargetsCount, status, health);
        }

    }
}
