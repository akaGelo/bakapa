package ru.vyukov.bakapa.controller.service.scheduler.executor;

import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.scheduling.TaskScheduler;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

public interface BackupTaskExecutor extends TaskScheduler, SchedulingTaskExecutor {

    /**
     * Run task in executor thread and wait execution
     *
     * @param synchronousTask
     */
    void invokeAndWait(Runnable synchronousTask) throws ExecutionException, InterruptedException;

    /**
     * Add task to invoke queue
     *
     * @param task
     */
    Future<?> invoke(Runnable task);

    void shutdownNow();

    ScheduledFuture<?> schedule(Runnable task, String cronTrigger);
}
