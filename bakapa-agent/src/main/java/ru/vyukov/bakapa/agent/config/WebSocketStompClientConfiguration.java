package ru.vyukov.bakapa.agent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.vyukov.bakapa.ws.AbstractWebSocketStompClientConfiguration;

@Configuration
@Import(AbstractWebSocketStompClientConfiguration.class)
public class WebSocketStompClientConfiguration {

}
