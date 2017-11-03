package ru.vyukov.bakapa.controller.controller;

import org.junit.Before;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.vyukov.bakapa.controller.controller.priv.BackupsTargetsOnAgentPrivateApiController;
import ru.vyukov.bakapa.controller.controller.superbase.AbstractMockMcvControllerTest;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.domain.backup.database.DatabaseBackupTarget;
import ru.vyukov.bakapa.controller.domain.backup.FilesystemBackupTarget;
import ru.vyukov.bakapa.controller.service.agents.AgentNotFoundException;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupTargetNotFoundException;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupsTargetsService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = BackupsTargetsOnAgentPrivateApiController.class)
public class PrivBackupsTargetsOnAgentMockBase extends AbstractMockMcvControllerTest {


    @MockBean
    private BackupsTargetsService backupsTargetsService;

    @MockBean
    private AgentsService agentsService;


    @Before
    public void whenGetBackupsTargets() throws AgentNotFoundException, BackupTargetNotFoundException {
        when(agentsService.getAgent(any(String.class))).thenAnswer((i) -> testAgent(i));
        when(backupsTargetsService.getBackupsTargets(any(Agent.class))).thenAnswer((inv) -> testBackupTargets(inv));
        when(backupsTargetsService.getBackupTarget(any(Agent.class), anyString())).thenAnswer((inv) -> testBackupTarget(inv));
    }



    private AbstractBackupTarget testBackupTarget(InvocationOnMock inv) {
        Agent agent = inv.getArgumentAt(0, Agent.class);
        return DatabaseBackupTarget.demo(agent, 1);
    }

    private Agent testAgent(InvocationOnMock invocationOnMock) {
        String agentId = invocationOnMock.getArgumentAt(0, String.class);
        return Agent.demo(agentId);
    }

    private List<AbstractBackupTarget> testBackupTargets(InvocationOnMock inv) {
        Agent agent = inv.getArgumentAt(0, Agent.class);
        List<AbstractBackupTarget> targets = Arrays.asList(
                DatabaseBackupTarget.demo(agent, 1),
                DatabaseBackupTarget.demo(agent, 2),
                FilesystemBackupTarget.demo(agent, 1));
        return targets;
    }


}
