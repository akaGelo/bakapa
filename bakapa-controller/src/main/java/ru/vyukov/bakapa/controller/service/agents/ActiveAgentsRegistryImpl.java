package ru.vyukov.bakapa.controller.service.agents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import ru.vyukov.bakapa.controller.domain.agent.AgentAndInfo;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.agent.Health;
import ru.vyukov.bakapa.controller.domain.backup.BackupTask;
import ru.vyukov.bakapa.dto.BackupTaskDTO;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static ru.vyukov.bakapa.domain.AgentStatus.OFFLINE;
import static ru.vyukov.bakapa.domain.AgentStatus.ONLINE;

/**
 * @author Oleg Vyukov
 */
@Component
public class ActiveAgentsRegistryImpl implements ActiveAgentsRegistry {


    private Set<Agent> onlineAgents = ConcurrentHashMap.newKeySet();

    private Map<Agent, Health> health = new ConcurrentHashMap<>();

    private SimpMessageSendingOperations sendingOperations;

    @Autowired
    public ActiveAgentsRegistryImpl(SimpMessageSendingOperations sendingOperations) {
        this.sendingOperations = sendingOperations;
    }


    @Override
    public void ping(Agent agent, Health health) {
        this.health.put(agent, health);
    }

    @Override
    public void setInfo(AgentAndInfo.AgentAndInfoBuilder agentAndInfoBuilder) {
        Agent agent = agentAndInfoBuilder.getAgent();
        if (isOnline(agent)) {
            agentAndInfoBuilder.status(ONLINE);
            agentAndInfoBuilder.health(health.get(agent));
        } else {
            agentAndInfoBuilder.status(OFFLINE);
        }
    }

    @Override
    public void connect(Agent agent) {
        onlineAgents.add(agent);
    }

    @Override
    public void disconnect(Agent agent) {
        onlineAgents.remove(agent);
        health.remove(agent);
    }


    @Override
    public boolean startBackup(BackupTask backupTask, Agent agent) {
        if (!isOnline(agent)) {
            return false;
        }
        sendingOperations.convertAndSendToUser(agent.getUsername(), BackupTaskDTO.DESTINATION, backupTask);
        return true;
    }

    @Override
    public boolean isOnline(Agent agent) {
        return onlineAgents.contains(agent);
    }
}
