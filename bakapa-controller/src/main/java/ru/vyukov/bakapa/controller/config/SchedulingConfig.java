package ru.vyukov.bakapa.controller.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import ru.vyukov.bakapa.controller.service.scheduler.executor.BackupTaskExecutor;
import ru.vyukov.bakapa.controller.service.scheduler.executor.BackupTaskExecutorImpl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
@EnableScheduling
public class SchedulingConfig implements SchedulingConfigurer {

    public static final int SCHEDULED_EXECUTOR_SERVICE_CORE_POOL_SIZE = 10;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(scheduledExecutorService());
    }

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        BasicThreadFactory threadFactory = new BasicThreadFactory.Builder()
                .daemon(true).namingPattern("scheduledExecutorService-%d").build();
        return Executors.newScheduledThreadPool(SCHEDULED_EXECUTOR_SERVICE_CORE_POOL_SIZE, threadFactory);
    }


    @Bean
    public BackupTaskExecutor backupTaskScheduler() {
        BackupTaskExecutor scheduler = new BackupTaskExecutorImpl();
        return scheduler;
    }


}
