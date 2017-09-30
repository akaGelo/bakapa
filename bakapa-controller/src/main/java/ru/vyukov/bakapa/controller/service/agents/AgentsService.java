package ru.vyukov.bakapa.controller.service.agents;

import java.util.Collections;
import java.util.List;

import org.bakapa.dto.AgentDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ru.vyukov.bakapa.controller.domain.Agent;

public interface AgentsService extends UserDetailsService {

	
	List<Agent> getAllAgetns();

	Agent newAgent(String agentId);

	
	@Override
	public Agent loadUserByUsername(String username) throws UsernameNotFoundException ;
}
