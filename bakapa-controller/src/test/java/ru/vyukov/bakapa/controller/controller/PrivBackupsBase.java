package ru.vyukov.bakapa.controller.controller;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vyukov.bakapa.controller.controller.superbase.AbstractIntegrationTest;
import ru.vyukov.bakapa.controller.domain.backup.Backup;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.repo.BackupRepository;
import ru.vyukov.bakapa.controller.repo.BackupsTargetsRepository;

abstract public class PrivBackupsBase extends AbstractIntegrationTest {

    @Autowired
    private BackupsTargetsRepository backupsTargetsRepository;

    @Autowired
    private BackupRepository backupRepository;

    private Backup backup;

    @Before
    public void setupBackups() {
        backupsTargetsRepository.deleteAll();
        backupRepository.deleteAll();
        AbstractBackupTarget target = AbstractBackupTarget.demo(agent, 1);

        target = backupsTargetsRepository.save(target);

        backup = backupRepository.save(Backup.demo(target));

    }
}
