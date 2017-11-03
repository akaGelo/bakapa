package ru.vyukov.bakapa.controller.service.backups;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.repo.BackupsTargetsRepository;

import java.util.List;

@Service
@Slf4j
public class BackupsTargetsServiceImpl implements BackupsTargetsService {


    private BackupsTargetsRepository backupsTargetsRepository;

    @Autowired
    public BackupsTargetsServiceImpl(BackupsTargetsRepository backupsTargetsRepository) {
        this.backupsTargetsRepository = backupsTargetsRepository;
    }

    @Override
    public List<AbstractBackupTarget> getBackupsTargets() {
        return backupsTargetsRepository.findAll();
    }

    @Override
    public List<AbstractBackupTarget> getBackupsTargets(Agent agent) {
        return backupsTargetsRepository.findAllByAgent(agent);
    }

    @Override
    public int getBackupsTargetsCount(Agent agent) {
        return 2;
    }

    @Override
    public AbstractBackupTarget getBackupTarget(String backupTargetId) throws BackupTargetNotFoundException {
        AbstractBackupTarget one = backupsTargetsRepository.findOne(backupTargetId);
        if (null == one) {
            throw new BackupTargetNotFoundException(backupTargetId);
        }
        return one;
    }


    @Override
    public AbstractBackupTarget getBackupTarget(Agent agent, String backupTargetId) throws BackupTargetNotFoundException {
        AbstractBackupTarget backupTarget = getBackupTarget(backupTargetId);
        if (backupTarget.getAgent().equals(agent)){
            return backupTarget;
        }
        throw new BackupTargetNotFoundException(agent,backupTargetId);
    }

    @Override
    public AbstractBackupTarget updateBackupTarget(AbstractBackupTarget backupTarget) {
        log.debug("update backup target " + backupTarget);
        return backupsTargetsRepository.save(backupTarget);
    }
}
