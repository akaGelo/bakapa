package ru.vyukov.bakapa.controller.service.scheduler;

import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;

import java.util.concurrent.ScheduledFuture;

public interface BackupSchedulerService {
    ScheduledFuture<?> update(AbstractBackupTarget abstractBackupTarget);
}
