package ru.vyukov.bakapa.agent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.vyukov.stomp.EnableStompClient;

import static org.junit.Assert.*;

/**
 * @author Oleg Vyukov
 */
@Configuration
@Profile("production")
public class TestConfig {

}