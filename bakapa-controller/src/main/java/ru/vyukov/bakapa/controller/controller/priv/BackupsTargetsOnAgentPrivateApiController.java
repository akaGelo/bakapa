package ru.vyukov.bakapa.controller.controller.priv;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.controller.domain.View;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.service.agents.AgentNotFoundException;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.backups.BackupsTargetsService;

import java.util.List;

@RestController
@RequestMapping("/private/agents/{agentId}/targets")

public class BackupsTargetsOnAgentPrivateApiController extends SuperPrivateController {


    @Autowired
    private BackupsTargetsService backupsTargetsService;

    @Autowired
    private AgentsService agentsService;


    @JsonView(Summary.class)
    @GetMapping("/")
    public List<AbstractBackupTarget> getBackupsTargets(@PathVariable("agentId") String agentId) throws AgentNotFoundException {
        Agent agent = agentsService.getAgent(agentId);
        return backupsTargetsService.getBackupsTargets(agent);
    }


    @PostMapping("/")
    public AbstractBackupTarget updateBackupTarget(@PathVariable("agentId") String agentId,
                                                   @RequestBody AbstractBackupTarget backupTarget) throws AgentNotFoundException {
        Agent agent = agentsService.getAgent(agentId);
        backupTarget.setAgent(agent);
        return backupsTargetsService.updateBackupTarget(backupTarget);
    }


}
