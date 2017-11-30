package ru.vyukov.bakapa.controller.service.agents;

import ru.vyukov.bakapa.controller.domain.agent.AgentAndInfo;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.agent.Health;
import ru.vyukov.bakapa.controller.domain.backup.BackupTask;

/**
 * @author Oleg Vyukov
 */


public interface ActiveAgentsRegistry {
    void connect(Agent agent);

    void disconnect(Agent agent);

    void ping(Agent agent, Health health);

    void setInfo(AgentAndInfo.AgentAndInfoBuilder agentAndInfoBuilder);

    /**
     * @param backup
     * @param agent
     * @return return true if agent online, else return false
     */
    boolean startBackup(BackupTask backup, Agent agent);

    boolean isOnline(Agent agent);
}
