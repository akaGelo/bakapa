package ru.vyukov.bakapa.controller.controller.priv.agents;

import com.fasterxml.jackson.annotation.JsonView;
import ru.vyukov.bakapa.domain.AgentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.controller.controller.pojo.AgentAndInfo;
import ru.vyukov.bakapa.controller.controller.priv.SuperPrivateController;
import ru.vyukov.bakapa.controller.domain.View.Full;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.agent.Agent.Credentials;
import ru.vyukov.bakapa.controller.service.agents.AgentNotFoundException;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupsTargetsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/private")
public class AgentsPrivateApiController extends SuperPrivateController {

    private AgentsService agentsService;

    private BackupsTargetsService backupsTargetsService;

    @Autowired
    public AgentsPrivateApiController(AgentsService agentsService, BackupsTargetsService backupsTargetsService) {
        this.agentsService = agentsService;
        this.backupsTargetsService = backupsTargetsService;
    }


    @JsonView(Summary.class)
    @GetMapping("/agents/")
    public List<AgentAndInfo> getAllAgents() {
        List<Agent> allAgents = agentsService.getAllAgents();

        List<AgentAndInfo> agentAndInfo = allAgents.stream().map(a -> {
            return AgentAndInfo.builder()
                    .agent(a)
                    .backupsTargetsCount(backupsTargetsService.getBackupsTargetsCount(a))
                    .status(AgentStatus.ONLINE)
                    .build();
        }).collect(Collectors.toList());
        return agentAndInfo;
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

