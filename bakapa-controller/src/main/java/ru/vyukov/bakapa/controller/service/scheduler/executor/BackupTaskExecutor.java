package ru.vyukov.bakapa.controller.service.scheduler.executor;

import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.scheduling.TaskScheduler;

public interface BackupTaskExecutor extends TaskScheduler,SchedulingTaskExecutor {

    /**
     * Run task in executor thread and wait execution
     * @param synchronousTask
     */
    void invokeAndWait(Runnable synchronousTask);
}
