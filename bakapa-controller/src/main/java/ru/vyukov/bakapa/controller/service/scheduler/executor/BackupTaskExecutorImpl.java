package ru.vyukov.bakapa.controller.service.scheduler.executor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

public class BackupTaskExecutorImpl extends ThreadPoolTaskScheduler implements BackupTaskExecutor {

    private static final int SINGLE_THREAD_SCHEDULER = 1;


    public BackupTaskExecutorImpl() {
        setThreadNamePrefix("BackupTaskExecutor-");
        setPoolSize(SINGLE_THREAD_SCHEDULER);
        setRemoveOnCancelPolicy(true);
        setDaemon(true);
    }

    @Override
    public void invokeAndWait(Runnable synchronousTask) throws ExecutionException, InterruptedException {
        Future<?> future = this.submit(synchronousTask);
        future.get();
    }

    @Override
    public Future<?> invoke(Runnable task) {
        return this.submit(task);
    }

    @Override
    public void shutdownNow() {
        setWaitForTasksToCompleteOnShutdown(true);
        super.shutdown();
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable task, String cronTrigger) {
        CronTrigger trigger = new CronTrigger(cronTrigger);
        return schedule(task, trigger);
    }
}
