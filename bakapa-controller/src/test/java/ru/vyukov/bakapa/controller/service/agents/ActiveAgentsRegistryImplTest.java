package ru.vyukov.bakapa.controller.service.agents;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import ru.vyukov.bakapa.controller.domain.agent.AgentAndInfo;
import ru.vyukov.bakapa.controller.domain.agent.AgentAndInfo.AgentAndInfoBuilder;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.agent.Health;
import ru.vyukov.bakapa.controller.domain.backup.BackupTask;
import ru.vyukov.bakapa.dto.backups.task.BackupTaskDTO;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author Oleg Vyukov
 */
@RunWith(MockitoJUnitRunner.class)
public class ActiveAgentsRegistryImplTest {

    private Agent agent1 = Agent.demo("testAgent1");
    private Agent agent2 = Agent.demo("testAgent2");


    @Mock
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @InjectMocks
    private ActiveAgentsRegistryImpl underTest;

    @Test
    public void ping() throws Exception {
        underTest.connect(agent1);

        final Health health = Health.demo();
        underTest.ping(agent1, health);

        AgentAndInfoBuilder builder = AgentAndInfo.builder(agent1);
        underTest.setInfo(builder);

        AgentAndInfo info = builderComplete(builder);
        assertEquals(health, info.getHealth());
    }

    @Test
    public void pingIgnoreOfflineAgent() throws Exception {
        underTest.ping(agent1, Health.demo());

        AgentAndInfoBuilder builder = AgentAndInfo.builder(agent1);
        underTest.setInfo(builder);

        AgentAndInfo info = builderComplete(builder);
        assertNull(info.getHealth());
    }

    @Test
    public void connect() throws Exception {
        underTest.connect(agent1);
        assertTrue(underTest.isOnline(agent1));
        assertFalse(underTest.isOnline(agent2));

        underTest.disconnect(Agent.demo("testAgent1"));

        assertFalse(underTest.isOnline(agent1));
    }


    @Test
    public void startBackup() throws Exception {
        underTest.connect(agent1);

        underTest.startBackup(BackupTask.demo(), agent1);

        underTest.startBackup(BackupTask.demo(), agent2);

        verify(simpMessageSendingOperations).convertAndSendToUser(
                eq(agent1.getAgentId()),
                eq(BackupTaskDTO.DESTINATION),
                any(BackupTask.class)
        );

        verify(simpMessageSendingOperations, never()).convertAndSendToUser(
                eq(agent2.getAgentId()),
                anyString(),
                any(BackupTask.class)
        );
    }


    private AgentAndInfo builderComplete(AgentAndInfoBuilder builder) {
        return builder.backupsTargetsCount(0).build();
    }
}