package ru.vyukov.bakapa.controller.repo;

import com.google.common.collect.Iterators;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vyukov.bakapa.controller.config.MongoDbConfig;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.domain.backup.target.FilesystemBackupTarget;
import ru.vyukov.bakapa.controller.domain.backup.target.database.DatabaseBackupTarget;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;
import static org.junit.Assert.*;
import static ru.vyukov.bakapa.controller.domain.agent.Agent.newAgent;
import static ru.vyukov.bakapa.controller.repo.AgentsRepositoryTest.cleanAgents;
import static ru.vyukov.bakapa.controller.repo.AgentsRepositoryTest.saveAgent;


public class BackupsTargetsRepositoryTest extends SuperRepoTest {


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
        backupsTargetsRepository.save(asList(btOne, btThree, btTwo));

        List<AbstractBackupTarget> firstAgentTargets = backupsTargetsRepository.findAllByAgent(agentOne);
        int sizeOne = firstAgentTargets.size();
        assertEquals(2, sizeOne);

        List<AbstractBackupTarget> secondAgentTargets = backupsTargetsRepository.findAllByAgent(agentTwo);
        int sizeTwo = secondAgentTargets.size();
        assertEquals(1, sizeTwo);


    }


    @Test
    public void findAllOrderByBackupTargetIdAsc() throws Exception {
        Agent agentOne = saveAgent(agentsRepository, newAgent("one"));
        DatabaseBackupTarget btOne = DatabaseBackupTarget.demo(agentOne, 1);
        FilesystemBackupTarget btTwo = FilesystemBackupTarget.demo(agentOne, 2);
        backupsTargetsRepository.save(asList(btOne, btTwo));


        Pageable pr = new PageRequest(0, 1);
        Page<AbstractBackupTarget> results = backupsTargetsRepository.findAllByOrderByBackupTargetIdAsc(pr);
        assertEquals(2, results.getTotalElements());
        int size = Iterators.size(results.iterator());
        assertEquals(1, size);
    }

    public static AbstractBackupTarget demoBackupTarget(BackupsTargetsRepository backupsTargetsRepository, AgentsRepository agentsRepository) {
        Agent agent = AgentsRepositoryTest.demo(agentsRepository);
        return backupsTargetsRepository.save(AbstractBackupTarget.demo(agent));
    }
}