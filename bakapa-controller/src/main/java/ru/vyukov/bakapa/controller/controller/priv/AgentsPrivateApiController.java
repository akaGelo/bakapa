package ru.vyukov.bakapa.controller.controller.priv;

import java.util.List;

import org.bakapa.dto.AgentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.vyukov.bakapa.controller.service.agents.AgentsService;

@RestController
public class AgentsPrivateApiController extends SuperPrivateController {

	@Autowired
	private AgentsService agentsService;

	@GetMapping("/agents/all")
	public List<AgentDTO> getAllAgents() {
		return convertDTO(agentsService.getAllAgetns(), AgentDTO.class);
	}
	
	
	

}
