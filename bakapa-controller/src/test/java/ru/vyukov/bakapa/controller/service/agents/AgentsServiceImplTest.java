package ru.vyukov.bakapa.controller.service.agents;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.repo.AgentsRepository;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AgentsServiceImplTest {
	@Mock
	private AgentsRepository agentsRepository;
	@InjectMocks
	private AgentsServiceImpl agentsServiceImpl;

	@Test
	public void testLoadUserByUsername() throws Exception {
		when(agentsRepository.findOne("testAgent")).thenReturn(DemoAgent.newDemoAgent());

		Agent agent = agentsServiceImpl.loadUserByUsername("testAgent");
		assertNotNull(agent);
	}

	@Test(expected = UsernameNotFoundException.class)
	public void testLoadUserFail() throws Exception {
		agentsServiceImpl.loadUserByUsername("testAgent");
	}

	@Test
	public void testNewAgent() throws Exception {
		when(agentsRepository.findOne("testAgent")).thenReturn(null);
		when(agentsRepository.save(any(Agent.class))).then(i -> i.getArgument(0));

		Agent newAgent = agentsServiceImpl.newAgent("testAgent");

		verify(agentsRepository).save(newAgent);
	}

}
