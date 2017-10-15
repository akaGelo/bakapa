package ru.vyukov.bakapa.controller.controller.priv;

import com.fasterxml.jackson.annotation.JsonView;
import org.bakapa.dto.agent.AgentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.controller.domain.Agent;
import ru.vyukov.bakapa.controller.domain.Agent.Credentials;
import ru.vyukov.bakapa.controller.domain.View;
import ru.vyukov.bakapa.controller.domain.View.Full;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.service.agents.AgentNotFoundException;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;

import java.util.List;

@RestController
@RequestMapping("/private")
public class AgentsPrivateApiController extends SuperPrivateController {

    @Autowired
    private AgentsService agentsService;


    @JsonView(Summary.class)
    @GetMapping("/agents/")
    public List<Agent> getAllAgents() {
        List<Agent> allAgents = agentsService.getAllAgents();
        return allAgents;
    }


    @JsonView(Full.class)
    @GetMapping("/agents/{agentId}/")
    public Agent getAgent(@PathVariable("agentId") String agentId) throws AgentNotFoundException {
        return agentsService.getAgent(agentId);
    }


    @JsonView(Credentials.class)
    @PostMapping("/agents/")
    public Agent createAgent(@RequestParam String agentId) {
        return agentsService.newAgent(agentId);
    }
}

