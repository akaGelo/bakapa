package ru.vyukov.bakapa.controller.controller;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.vyukov.bakapa.controller.BakapaControllerApplication;
import ru.vyukov.bakapa.controller.config.SecurityConfig;
import ru.vyukov.bakapa.controller.domain.agent.AgentAndInfo;
import ru.vyukov.bakapa.controller.controller.priv.agents.AgentsPrivateApiController;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.agent.Health;
import ru.vyukov.bakapa.controller.service.agents.AgentNotFoundException;
import ru.vyukov.bakapa.controller.service.agents.ActiveAgentsRegistry;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupsTargetsService;
import ru.vyukov.bakapa.domain.AgentStatus;
import ru.vyukov.bakapa.dto.HealthDTO;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AgentsPrivateApiController.class)
@Import(SecurityConfig.class)
@ContextConfiguration(classes = BakapaControllerApplication.class)
abstract public class PrivAgentsBase {

    private final Agent agent1 = Agent.newAgent("testAgentId-1");
    private final Agent agent2 = Agent.newAgent("testAgentId-2");
    @MockBean
    private AgentsService agentsService;

    @MockBean
    private BackupsTargetsService backupsTargetsService;

    @MockBean
    private ActiveAgentsRegistry activeAgentsRegistry;


    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() throws AgentNotFoundException {
        whenAgentService();


        doAnswer(inv -> {
            AgentAndInfo.AgentAndInfoBuilder builder = inv.getArgument(0);
            builder.backupsTargetsCount(2);
            return null;
        }).when(backupsTargetsService).setInfo(any());


        doAnswer(inv -> {
            AgentAndInfo.AgentAndInfoBuilder builder = inv.getArgument(0);
            builder.status(AgentStatus.ONLINE).health(Health.demo());
            return null;
        }).when(activeAgentsRegistry).setInfo(any());

        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    private void whenAgentService() throws AgentNotFoundException {
        Answer<Object> answer = invocation -> {
            String agentId = invocation.getArgument(0);
            return Agent.newAgent(agentId);
        };
        when(agentsService.newAgent(any(String.class))).then(answer);

        when(agentsService.getAgent(any(String.class))).then(answer);

        when(agentsService.getAllAgents()).thenReturn(asList(
                agent1,
                agent2
        ));
    }

}
