package ru.vyukov.bakapa.controller.controller.priv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.controller.domain.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.domain.Agent;
import ru.vyukov.bakapa.controller.service.agents.AgentNotFoundException;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.backups.BackupsTargetsService;

import java.util.List;

@RestController
public class BackupsTargetsOnAgentPrivateApiController extends SuperPrivateController {


    @Autowired
    private BackupsTargetsService backupsTargetsService;

    @Autowired
    private AgentsService agentsService;


    @GetMapping("/agents/{agentId}/targets/")
    public List<AbstractBackupTarget> getBackupsTargets(@PathVariable("agentId") String agentId) throws AgentNotFoundException {
        Agent agent = agentsService.getAgent(agentId);
        return backupsTargetsService.getBackupsTargets(agent);
    }


    @PostMapping("/agents/{agentId}/targets/")
    public void createBackupTarget(@PathVariable("agentId") String agentId,
                                   @RequestBody @Validated AbstractBackupTarget backupTarget) throws AgentNotFoundException {
        backupsTargetsService.createBackupTarget(backupTarget);
    }


    @GetMapping("/agents/{agentId}/targets/{backupsTargetId}/backups/")
    public List<AbstractBackupTarget> getBackups(@PathVariable("agentId") String agentId,
                                                 @PathVariable("backupTargetId") String backupTargetId) throws AgentNotFoundException {
        Agent agent = agentsService.getAgent(agentId);
        return backupsTargetsService.getBackupsTargets(agent);
    }


}
