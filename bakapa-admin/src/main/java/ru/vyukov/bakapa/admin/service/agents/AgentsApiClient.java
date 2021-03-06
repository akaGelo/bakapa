package ru.vyukov.bakapa.admin.service.agents;

import ru.vyukov.bakapa.dto.agent.AgentAndCredentialsDTO;
import ru.vyukov.bakapa.dto.agent.AgentAndInfoDTO;
import ru.vyukov.bakapa.dto.agent.AgentDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "bakapa-controller")
@RequestMapping("/private/agents")
public interface AgentsApiClient {


    @GetMapping("/")
    public List<AgentAndInfoDTO> getAgents();

    @GetMapping("/{agentId}/")
    public AgentDTO getAgent(@PathVariable("agentId") String agentId);


    @PostMapping("/")
    public AgentAndCredentialsDTO create(@RequestParam("agentId") String agentId);


}
