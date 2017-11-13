package ru.vyukov.bakapa.controller.service.backups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.Backup;
import ru.vyukov.bakapa.controller.domain.backup.BackupLogItem;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.repo.BackupRepository;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupsTargetsService;

import java.util.List;

@Service
public class BackupsServiceImpl implements BackupsService {


    private BackupRepository backupRepository;

    private BackupsTargetsService backupsTargetsService;


    private BackupsLogsService backupsLogsService;

    @Autowired
    public BackupsServiceImpl(BackupRepository backupRepository, BackupsTargetsService backupsTargetsService, BackupsLogsService backupsLogsService) {
        this.backupRepository = backupRepository;
        this.backupsTargetsService = backupsTargetsService;
        this.backupsLogsService = backupsLogsService;
    }

    @Override
    public Backup createNewBackup(AbstractBackupTarget backupTarget) throws CreateBackupException {
        Backup prevBackup = backupRepository.findOneByBackupTargetOrderByStartTimestampAsc(backupTarget);
        Backup newBackup;
        if (null != prevBackup && prevBackup.isNotFinished()) {
            newBackup = Backup.newStoppedBackup(backupTarget);
            newBackup = backupRepository.save(newBackup);

            BackupLogItem logItem = backupsLogsService.save(BackupLogItem.startError(newBackup, "Previous backup not completed"));

            throw new CreateBackupException(newBackup, logItem);
        }
        newBackup = Backup.newBackup(backupTarget);
        newBackup = backupRepository.save(newBackup);
        return newBackup;
    }

    @Override
    public Page<Backup> getBackups(Pageable pageable) {
        return backupRepository.findAll(pageable);
    }

    @Override
    public Page<Backup> getBackups(AbstractBackupTarget backupTarget, Pageable pageable) {
        return backupRepository.findAllByBackupTarget(backupTarget, pageable);
    }

    @Override
    public Page<Backup> getBackups(Agent agent, Pageable pageable) {
        List<AbstractBackupTarget> backupsTargets = backupsTargetsService.getBackupsTargets(agent);
        return backupRepository.findAllByBackupTarget(backupsTargets, pageable);
    }
}
