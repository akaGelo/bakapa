package ru.vyukov.bakapa.admin.service.agents;

import org.bakapa.dto.agent.AgentAndCredentialsDTO;
import org.bakapa.dto.agent.AgentDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vyukov.bakapa.admin.controller.backups.BackupsController;

import java.util.List;

@FeignClient(value = "bakapa-controller")
@RequestMapping("/private")
public interface AgentsApiClient {


    @GetMapping("/agents/")
    public List<AgentDTO> getAgents();


    @PostMapping("/agents/")
    public AgentAndCredentialsDTO create(@RequestParam("agentId") String agentId);
}
