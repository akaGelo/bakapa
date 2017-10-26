package ru.vyukov.bakapa.controller.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.vyukov.bakapa.controller.config.GeneralConfig;
import ru.vyukov.bakapa.controller.config.SecurityConfig;
import ru.vyukov.bakapa.controller.domain.Agent;
import ru.vyukov.bakapa.controller.repo.AgentsRepository;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.agents.AgentsServiceImpl;

@Configuration
@Import(SecurityConfig.class)
public class WebMvcTestConfig extends GeneralConfig {

	/**
	 * This service return user on all requests. Password equals  username parameter
	 * @return
	 */
	@Bean
	AgentsService agentsService() {
		AgentsService agentsService = mock(AgentsService.class);
		when(agentsService.loadUserByUsername("testUser")).thenAnswer((i) -> {
			String username = i.getArgumentAt(0, String.class);
			Agent newAgent = Agent.newAgent(username);
			newAgent.setPassword(username);
			return newAgent;
		});
		return agentsService;
	}

}
