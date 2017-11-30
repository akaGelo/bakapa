package ru.vyukov.bakapa.controller.controller.priv.agents;

import com.fasterxml.jackson.annotation.JsonView;
import ru.vyukov.bakapa.controller.service.agents.ActiveAgentsRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.controller.domain.agent.AgentAndInfo;
import ru.vyukov.bakapa.controller.controller.priv.SuperPrivateController;
import ru.vyukov.bakapa.controller.domain.View.Full;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.View.Credentials;
import ru.vyukov.bakapa.controller.service.agents.AgentNotFoundException;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupsTargetsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/private")
public class AgentsPrivateApiController extends SuperPrivateController {

    private AgentsService agentsService;

    private ActiveAgentsRegistry activeAgentsRegistry;

    private BackupsTargetsService backupsTargetsService;

    @Autowired
    public AgentsPrivateApiController(AgentsService agentsService, ActiveAgentsRegistry activeAgentsRegistry, BackupsTargetsService backupsTargetsService) {
        this.agentsService = agentsService;
        this.activeAgentsRegistry = activeAgentsRegistry;
        this.backupsTargetsService = backupsTargetsService;
    }


    @JsonView(Summary.class)
    @GetMapping("/agents/")
    public List<AgentAndInfo> getAllAgents() {
        List<Agent> allAgents = agentsService.getAllAgents();

        return allAgents.stream().map(AgentAndInfo::builder)

                .peek(activeAgentsRegistry::setInfo)
                .peek(backupsTargetsService::setInfo)

                .map(AgentAndInfo.AgentAndInfoBuilder::build)
                .collect(Collectors.toList());
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

