package ru.vyukov.bakapa.controller.service.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.Backup;
import ru.vyukov.bakapa.controller.domain.backup.BackupTask;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.domain.config.StorageConfig;
import ru.vyukov.bakapa.controller.service.agents.ActiveAgentsRegistry;
import ru.vyukov.bakapa.controller.service.backups.BackupsService;
import ru.vyukov.bakapa.controller.service.backups.CreateBackupException;
import ru.vyukov.bakapa.controller.service.backupsstorage.BackupsStorageService;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupsTargetsService;
import ru.vyukov.bakapa.controller.service.scheduler.executor.BackupTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 *
 */
@Service
@Slf4j
public class BackupSchedulerServiceImpl implements BackupSchedulerService {

    private final BackupsTargetsService targetsService;
    private final BackupsService backupsService;
    private final BackupsStorageService backupsStorageService;
    private final ActiveAgentsRegistry agentsRegistry;


    private BackupTaskExecutor taskScheduler;

    private Map<AbstractBackupTarget, ScheduledFuture<?>> tasks = new ConcurrentHashMap<>();

    public BackupSchedulerServiceImpl(BackupsTargetsService backupsTargetsService, BackupsService backupsService, BackupsStorageService backupsStorageService, ActiveAgentsRegistry agentsRegistry, BackupTaskExecutor taskScheduler) {
        this.targetsService = backupsTargetsService;
        this.backupsService = backupsService;
        this.backupsStorageService = backupsStorageService;
        this.agentsRegistry = agentsRegistry;
        this.taskScheduler = taskScheduler;
    }


    @PostConstruct
    public void init() {
        log.info("Init BackupSchedulerService");
        Pageable pr = new PageRequest(0, 20);
        while (true) {
            Page<AbstractBackupTarget> backupsTargets = targetsService.getBackupsTargets(pr);
            createTargetTasks(backupsTargets);
            if (!backupsTargets.hasNext()) {
                break;
            }
            pr = pr.next();
        }
    }

    private void createTargetTasks(Page<AbstractBackupTarget> backupsTargets) {
        for (AbstractBackupTarget backupTarget : backupsTargets) {
            if (tasks.containsKey(backupTarget)) {
                destroyExecutorOnIncorrectState(backupTarget);
            }
            ScheduledFuture<?> scheduledFuture = createTargetTask(backupTarget);
            tasks.put(backupTarget, scheduledFuture);


        }
    }

    private void destroyExecutorOnIncorrectState(AbstractBackupTarget bt) {
        String msg = bt + " already loaded. Task executor stopped. This is program bug";
        log.error(msg);
        taskScheduler.shutdownNow();
        throw new IllegalStateException(msg);
    }

    private ScheduledFuture<?> createTargetTask(AbstractBackupTarget backupTarget) {
        return taskScheduler.schedule(startBackup(backupTarget), backupTarget.getTrigger());
    }

    private Runnable startBackup(AbstractBackupTarget backupTarget) {
        return () -> {
            Backup backup = null;
            try {
                backup = backupsService.createNewBackup(backupTarget);
                // no error backups
                Agent agent = backupTarget.getAgent();
                StorageConfig storage = backupsStorageService.getStorage();
                BackupTask backupTask = new BackupTask(backup.getBackupId(), backup.getBackupTarget(), storage);
                if (!agentsRegistry.startBackup(backupTask, agent)) {
                    //TODO add log of agent offline
                    //TODO set error result
                    //backupsService.
                }
            } catch (CreateBackupException e) {
                log.warn("start backup problem", e);
            }
        };
    }


    @Override
    synchronized public ScheduledFuture<?> update(AbstractBackupTarget abstractBackupTarget) {
        ScheduledFuture<?> prevFuture = tasks.get(abstractBackupTarget);
        if (null != prevFuture) {
            prevFuture.cancel(false);
            log.info("update  backup task " + abstractBackupTarget);
        } else {
            log.info("create  backup task " + abstractBackupTarget);
        }
        ScheduledFuture<?> newFuture = createTargetTask(abstractBackupTarget);
        tasks.put(abstractBackupTarget, newFuture);

        return newFuture;
    }
}
