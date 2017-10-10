package ru.vyukov.bakapa.controller.service.scheduler;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BackupSchedulerServiceImpl implements BackupSchedulerService {

    private TaskScheduler taskScheduler;

    public BackupSchedulerServiceImpl(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }


    @PostConstruct
    synchronized public void init() {
        taskScheduler.schedule(() -> {
            System.out.println("5 second");
        }, new CronTrigger("*/5 * * * * *"));
    }
}
