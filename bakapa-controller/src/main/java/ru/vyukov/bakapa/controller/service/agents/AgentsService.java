package ru.vyukov.bakapa.controller.service.agents;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ru.vyukov.bakapa.controller.domain.agent.Agent;

public interface AgentsService extends UserDetailsService {

	
	List<Agent> getAllAgents();

	Agent newAgent(String agentId);

	
	@Override
	public Agent loadUserByUsername(String username) throws UsernameNotFoundException ;

    Agent getAgent(String agentId) throws AgentNotFoundException;
}
