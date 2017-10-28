package ru.vyukov.bakapa.controller.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.vyukov.bakapa.controller.config.GeneralConfig;
import ru.vyukov.bakapa.controller.config.SecurityConfig;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.service.agents.AgentNotFoundException;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;

@Configuration
@Import(SecurityConfig.class)
public class WebMvcTestConfig extends GeneralConfig {



}
