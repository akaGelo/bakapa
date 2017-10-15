package ru.vyukov.bakapa.admin.service.agents;

import org.bakapa.dto.agent.AgentAndCredentialsDTO;
import org.bakapa.dto.agent.AgentDTO;
import org.bakapa.dto.backups.AbstractBackupTargetDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.admin.controller.backups.BackupsController;

import java.util.List;

@FeignClient(value = "bakapa-controller")
@RequestMapping("/private")
public interface AgentsApiClient {


    @GetMapping("/agents/")
    public List<AgentDTO> getAgents();

    @GetMapping("/agents/{agentId}/")
    public AgentDTO getAgent(@PathVariable("agentId") String  agentId);


    @PostMapping("/agents/")
    public AgentAndCredentialsDTO create(@RequestParam("agentId") String agentId);


    //-----------------------------------------------------------------------------------------------------------------


    @GetMapping("/agents/backups/")
    public List<AbstractBackupTargetDTO> getBackups();

    @GetMapping("/agents/{agentId}/backups/")
    public List<AbstractBackupTargetDTO> getBackups(@PathVariable("agentId") String agentId);



}
