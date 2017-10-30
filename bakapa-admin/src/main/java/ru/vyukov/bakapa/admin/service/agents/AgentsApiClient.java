package ru.vyukov.bakapa.admin.service.agents;

import org.bakapa.dto.agent.AgentAndCredentialsDTO;
import org.bakapa.dto.agent.AgentAndInfoDTO;
import org.bakapa.dto.agent.AgentDTO;
import org.bakapa.dto.backups.AbstractBackupTargetDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.admin.controller.backups.BackupsController;

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
