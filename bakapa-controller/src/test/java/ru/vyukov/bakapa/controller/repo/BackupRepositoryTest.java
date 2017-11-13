package ru.vyukov.bakapa.controller.repo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.Backup;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;


import java.time.Instant;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class BackupRepositoryTest extends SuperRepoTest {


    @Autowired
    private BackupsTargetsRepository backupsTargetsRepository;

    @Autowired
    private BackupRepository backupRepository;

    @Autowired
    private AgentsRepository agentRepository;

    private AbstractBackupTarget abstractBackupTarget;
    private Agent agent;


    @Before
    public void cleanDb() {
        backupRepository.deleteAll();

        abstractBackupTarget = BackupsTargetsRepositoryTest.demoBackupTarget(backupsTargetsRepository, agentRepository);
        agent = abstractBackupTarget.getAgent();
    }


    @Test
    public void findAllByBackupTarget() throws Exception {
        Backup backup = Backup.demo(abstractBackupTarget);
        backupRepository.save(backup);

        Pageable pageable = new PageRequest(0, 20);
        Page<Backup> backups = backupRepository.findAllByBackupTarget(asList(abstractBackupTarget), pageable);

        assertEquals(backup, backups.iterator().next());
    }

    @Test
    public void finOneByBackupTargetOrderByStartDateAsc() throws Exception {
        Backup backupOne = Backup.demo(abstractBackupTarget);
        backupRepository.save(backupOne);

        Backup backupTwo = Backup.demo(abstractBackupTarget);
        backupTwo.setStartTimestamp(Instant.now().minusSeconds(600));
        backupRepository.save(asList(backupOne, backupTwo));


        Backup backup = backupRepository.findOneByBackupTargetOrderByStartTimestampAsc(abstractBackupTarget);
        assertEquals(backup, backupTwo);
    }

}