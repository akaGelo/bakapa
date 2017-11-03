package ru.vyukov.bakapa.controller.repo;

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
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.domain.backup.FilesystemBackupTarget;
import ru.vyukov.bakapa.controller.domain.backup.database.DatabaseBackupTarget;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static ru.vyukov.bakapa.controller.domain.agent.Agent.newAgent;
import static ru.vyukov.bakapa.controller.repo.AgentsRepositoryTest.cleanAgents;
import static ru.vyukov.bakapa.controller.repo.AgentsRepositoryTest.saveAgent;

@RunWith(SpringRunner.class)
@Import({MongoDbConfig.class, ValidationAutoConfiguration.class})
@DataMongoTest
@ActiveProfiles("test")
public class BackupsTargetsRepositoryTest {

    @Autowired
    private BackupsTargetsRepository backupsTargetsRepository;

    @Autowired
    private AgentsRepository agentsRepository;

    @Before
    public void cleanTargets() throws Exception {
        cleanAgents(agentsRepository);
        backupsTargetsRepository.deleteAll();
    }

    @Test
    public void findAllByAgent() throws Exception {
        Agent agentOne = saveAgent(agentsRepository, newAgent("one"));
        Agent agentTwo = saveAgent(agentsRepository, newAgent("two"));

        DatabaseBackupTarget btOne = DatabaseBackupTarget.demo(agentOne, 1);
        DatabaseBackupTarget btTwo = DatabaseBackupTarget.demo(agentOne, 2);

        FilesystemBackupTarget btThree = FilesystemBackupTarget.demo(agentTwo, 3);
        backupsTargetsRepository.save(asList(btOne,btThree,btTwo));

        List<AbstractBackupTarget> firstAgentTargets = backupsTargetsRepository.findAllByAgent(agentOne);
        int sizeOne = firstAgentTargets.size();
        assertEquals(2, sizeOne);

        List<AbstractBackupTarget> secondAgentTargets = backupsTargetsRepository.findAllByAgent(agentTwo);
        int sizeTwo = secondAgentTargets.size();
        assertEquals(1, sizeTwo);


    }



}