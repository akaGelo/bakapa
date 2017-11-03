package ru.vyukov.bakapa.controller.controller.priv;

import com.fasterxml.jackson.annotation.JsonView;
import org.bakapa.domain.BackupTargetStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.controller.controller.pojo.BackupTargetAndInfo;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.domain.backup.BackupTargetExecutionInfo;
import ru.vyukov.bakapa.controller.service.agents.AgentNotFoundException;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.backups.BackupTargetNotFoundException;
import ru.vyukov.bakapa.controller.service.backups.BackupsTargetsService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/private/agents/{agentId}/targets")

public class BackupsTargetsOnAgentPrivateApiController extends SuperPrivateController {


    @Autowired
    private BackupsTargetsService backupsTargetsService;

    @Autowired
    private AgentsService agentsService;

    @ModelAttribute("agent")
    private Agent getAgent(@PathVariable("agentId") String agentId) throws AgentNotFoundException {
        return agentsService.getAgent(agentId);
    }


    @JsonView(Summary.class)
    @GetMapping("/")
    public List<BackupTargetAndInfo> getBackupsTargets(@ModelAttribute("agent") Agent agent) {
        List<AbstractBackupTarget> backupsTargets = backupsTargetsService.getBackupsTargets(agent);
        return backupsTargets.stream().map(bt -> getBackupTargetAndInfo(bt)).collect(Collectors.toList());
    }


    @JsonView(Summary.class)
    @GetMapping("/{backupTargetId}/")
    public AbstractBackupTarget getBackupTarget(@ModelAttribute("agent") Agent agent, @PathVariable("backupTargetId") String backupTargetId) throws BackupTargetNotFoundException {
        return backupsTargetsService.getBackupTarget(agent, backupTargetId);
    }

    private BackupTargetAndInfo getBackupTargetAndInfo(AbstractBackupTarget bt) {
        return BackupTargetAndInfo.builder()
                .backupTarget(bt)
                .executionInfo(
                        BackupTargetExecutionInfo.builder()
                                .lastExecutionTimestamp(Instant.now())
                                .lastStatus(BackupTargetStatus.ERROR)
                                .lastSizeBytes(0)
                                .nextExecutionTimestamp(Instant.now().plusSeconds(200))
                                .build()
                )
                .build();
    }


    @PostMapping("/")
    public AbstractBackupTarget updateBackupTarget(@ModelAttribute("agent") Agent agent,
                                                   @RequestBody AbstractBackupTarget backupTarget) throws AgentNotFoundException {
        backupTarget = backupTarget.agent(agent);
        return backupsTargetsService.updateBackupTarget(backupTarget);
    }


}
