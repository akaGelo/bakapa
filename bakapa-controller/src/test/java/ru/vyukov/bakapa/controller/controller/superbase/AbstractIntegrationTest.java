package ru.vyukov.bakapa.controller.controller.superbase;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.vyukov.bakapa.controller.BakapaControllerApplication;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.repo.AgentsRepository;
import ru.vyukov.bakapa.controller.service.agents.AgentNotFoundException;

/**
 * Test for  cloud contract verify
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BakapaControllerApplication.class)
@Import(WebMvcTestConfig.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
abstract public class AbstractIntegrationTest extends SuperControllerTest {


    @Autowired
    private AgentsRepository agentsRepository;

    @Autowired
    private MockMvc mockMvc;

    protected Agent agent;


    @Before
    public void setupMockMvc() throws AgentNotFoundException {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }


    @Before
    public void setupTestAgent() {
        agent = agentsRepository.findOne(TEST_AGENT_ID);
        if (null == agent) {
            agent = agentsRepository.save(Agent.demo(TEST_AGENT_ID));
        }
    }
}
