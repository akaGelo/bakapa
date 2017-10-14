package ru.vyukov.bakapa.controller.service.backups;

import org.springframework.stereotype.Service;
import ru.vyukov.bakapa.controller.domain.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.domain.Agent;

import java.util.List;

@Service
public class BackupsTargetsServiceImpl implements BackupsTargetsService {
    @Override
    public List<AbstractBackupTarget> getBackupsTargets() {
        return null;
    }

    @Override
    public List<AbstractBackupTarget> getBackupsTargets(Agent agent) {
        return null;
    }

    @Override
    public AbstractBackupTarget getBackupTarget(String backupTargetId) throws BackupTargetNotFoundException {
        return null;
    }

    @Override
    public void createBackupTarget(AbstractBackupTarget backupTarget) {

    }
}
