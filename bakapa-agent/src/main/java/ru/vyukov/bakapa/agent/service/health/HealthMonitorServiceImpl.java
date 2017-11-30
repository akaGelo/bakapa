package ru.vyukov.bakapa.agent.service.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.vyukov.bakapa.agent.service.system.DiskInspector;
import ru.vyukov.bakapa.domain.AgentStatus;
import ru.vyukov.bakapa.dto.HealthDTO;

import java.lang.management.OperatingSystemMXBean;

@Service
public class HealthMonitorServiceImpl implements HealthMonitorService {


    private final SimpMessageSendingOperations simpMessagingTemplate;

    private DiskInspector diskInspector;

    private OperatingSystemMXBean operatingSystemMXBean;

    @Autowired
    public HealthMonitorServiceImpl(SimpMessageSendingOperations simpMessagingTemplate, DiskInspector diskInspector, OperatingSystemMXBean operatingSystemMXBean) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.diskInspector = diskInspector;
        this.operatingSystemMXBean = operatingSystemMXBean;
    }


    @Scheduled(fixedDelay = 10_000)
    public void schedule() {
        simpMessagingTemplate.convertAndSend(HealthDTO.DESTINATION, health());
    }

    private HealthDTO health() {
        return HealthDTO.builder()
                .sla(operatingSystemMXBean.getSystemLoadAverage())
                .freeDiskSpaceBytes(diskInspector.getFreeSpace())
                .build();

    }


}