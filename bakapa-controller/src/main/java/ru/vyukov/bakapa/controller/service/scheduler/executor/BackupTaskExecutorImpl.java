package ru.vyukov.bakapa.controller.service.scheduler.executor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

public class BackupTaskExecutorImpl extends ThreadPoolTaskScheduler implements BackupTaskExecutor {

    private static final int SINGLE_THREAD_SCHEDULER = 1;


    public BackupTaskExecutorImpl() {
        setThreadNamePrefix("BackupTaskExecutor-");
        setPoolSize(SINGLE_THREAD_SCHEDULER);
        setRemoveOnCancelPolicy(true);
        setDaemon(true);
    }
}
