package ru.vyukov.bakapa.controller.service.scheduler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.service.backups.BackupsService;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupsTargetsService;
import ru.vyukov.bakapa.controller.service.scheduler.executor.BackupTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 *
 */
@Service
public class BackupSchedulerServiceImpl implements BackupSchedulerService {
    private final BackupsTargetsService targetsService;
    private final BackupsService backupsService;

    /**
     * All modifying operations must be performed through the executor. This will avoid errors in the future, leading all modifications to one thread.
     */
    private BackupTaskExecutor taskScheduler;

    private Set<ScheduledFuture<?>> tasks = ConcurrentHashMap.newKeySet();

    public BackupSchedulerServiceImpl(BackupsTargetsService backupsTargetsService, BackupsService backupsService, BackupTaskExecutor taskScheduler) {
        this.targetsService = backupsTargetsService;
        this.backupsService = backupsService;
        this.taskScheduler = taskScheduler;
    }


    @PostConstruct
    public void init() { //
//        taskScheduler.invokeAndWait(() -> {
//    //обновляй
//
//        });

    }
}
