package ru.vyukov.bakapa.controller.service.agents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.vyukov.bakapa.controller.domain.Agent;
import ru.vyukov.bakapa.controller.repo.AgentsRepository;

@Service
public class AgentsServiceImpl implements AgentsService {

	private AgentsRepository agentsRepository;

	public AgentsServiceImpl(@Autowired AgentsRepository agentsRepository) {
		this.agentsRepository = agentsRepository;
	}

	@Override
	public Agent loadUserByUsername(String username) throws UsernameNotFoundException {
		Agent findOne = agentsRepository.findOne(username);
//		if (null == findOne) {
//			throw new UsernameNotFoundException(username + " not agent usernameё");
//		}
		
		findOne = Agent.newAgent(username);
		findOne.setPassword(username);
		

		return findOne;
	}

	@Override
	public List<Agent> getAllAgetns() {
		return agentsRepository.findAll();
	}

	@Override
	public Agent newAgent(String agentId) {
		Agent findOne = agentsRepository.findOne(agentId);
		if (null != findOne) {
			throw new IllegalArgumentException("Name already used");
		}
		Agent agent = Agent.newAgent(agentId);
		agent = agentsRepository.save(agent);
		return agent;
	}

}