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

	/**
	 * For spring security
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public Agent loadUserByUsername(String username) throws UsernameNotFoundException {
		Agent findOne = agentsRepository.findOne(username);
		if (null == findOne) {
			throw new UsernameNotFoundException(username + " not agent username");
		}
		return findOne;
	}

	@Override
	public Agent getAgent(String agentId) throws AgentNotFoundException {
		Agent agent = agentsRepository.findOne(agentId);
		if (null == agent) {
			throw new AgentNotFoundException(agentId);
		}
		return agent;
	}

	@Override
	public List<Agent> getAllAgents() {
		List<Agent> agents = agentsRepository.findAll();
		return agents;
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