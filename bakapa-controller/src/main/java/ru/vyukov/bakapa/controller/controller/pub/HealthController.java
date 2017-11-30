package ru.vyukov.bakapa.controller.controller.pub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import ru.vyukov.bakapa.controller.config.utils.AuthenticationAgent;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.agent.Health;
import ru.vyukov.bakapa.controller.service.agents.ActiveAgentsRegistry;
import ru.vyukov.bakapa.dto.HealthDTO;

import java.security.Principal;

import static ru.vyukov.bakapa.controller.config.utils.AuthenticationAgentArgumentResolver.extractAgent;

/**
 * @author gelo
 */
@Slf4j
@RestController
public class HealthController {

    @Autowired
    private ActiveAgentsRegistry activeAgentsRegistry;

    @MessageMapping(HealthDTO.DESTINATION)
    public void health(@AuthenticationAgent Agent agent, Health health) {
        log.debug("Health received " + agent + health);
        activeAgentsRegistry.ping(agent,health);
    }


    @EventListener
    public void onConnected(SessionConnectedEvent event) {
        Principal user = event.getUser();
        log.debug("Agent connected " + user);
        activeAgentsRegistry.connect(extractAgent(user));
    }


    @EventListener
    public void onDisconnect(SessionDisconnectEvent event) {
        Principal user = event.getUser();
        log.debug("Agent disconnected " + event.getSessionId());
        activeAgentsRegistry.disconnect(extractAgent(user));
    }

}