package ru.vyukov.bakapa.controller.repo;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vyukov.bakapa.controller.config.MongoDbConfig;
import ru.vyukov.bakapa.controller.domain.agent.Agent;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Import({MongoDbConfig.class, ValidationAutoConfiguration.class})
@DataMongoTest
@ActiveProfiles("test")
public class AgentsRepositoryTest {

    @Autowired
    private AgentsRepository agentsRepository;

    @Before
    public void cleanAgents() throws Exception {
        cleanAgents(agentsRepository);
    }


    @Test
    public void test() {
        String agentId = RandomStringUtils.randomAlphabetic(16);
        Agent agent = Agent.demo(agentId);
        Agent save = agentsRepository.save(agent);
        Agent read = agentsRepository.findOne(agentId);

        assertEquals(save, read);
        assertEquals(agent, read);
    }

    static public void cleanAgents(AgentsRepository agentsRepository) {
        agentsRepository.deleteAll();
    }


    static public Agent saveAgent(AgentsRepository agentsRepository, Agent agent) {
        return agentsRepository.save(agent);
    }
}