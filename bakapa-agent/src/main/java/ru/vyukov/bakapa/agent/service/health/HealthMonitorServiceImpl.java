package ru.vyukov.bakapa.agent.service.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthAggregator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Map;

import static org.springframework.util.Assert.notNull;

@Service
public class HealthMonitorServiceImpl implements HealthMonitorService {


    private final CompositeHealthIndicator healthIndicator;

    private final SimpMessageSendingOperations simpMessagingTemplate;

    @Autowired
    public HealthMonitorServiceImpl(HealthAggregator healthAggregator,
                                    Map<String, HealthIndicator> healthIndicators, SimpMessageSendingOperations simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        notNull(healthAggregator, "HealthAggregator must not be null");
        notNull(healthIndicators, "HealthIndicators must not be null");

        CompositeHealthIndicator healthIndicator = new CompositeHealthIndicator(healthAggregator);
        for (Map.Entry<String, HealthIndicator> entry : healthIndicators.entrySet()) {
            healthIndicator.addHealthIndicator(getKey(entry.getKey()), entry.getValue());
        }
        this.healthIndicator = healthIndicator;
    }


    @Scheduled(fixedDelay = 10_000)
    public void schedule() {
        Health health = healthIndicator.health();

        simpMessagingTemplate.convertAndSend("/app/health", health);
    }


    private String getKey(String name) {
        int index = name.toLowerCase().indexOf("healthindicator");
        if (index > 0) {
            return name.substring(0, index);
        }
        return name;
    }
}