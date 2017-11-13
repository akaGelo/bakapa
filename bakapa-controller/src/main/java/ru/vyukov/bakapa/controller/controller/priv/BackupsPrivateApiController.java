package ru.vyukov.bakapa.controller.controller.priv;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.controller.domain.View;
import ru.vyukov.bakapa.controller.domain.View.Full;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.Backup;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.service.agents.AgentNotFoundException;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.backups.BackupsService;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupTargetNotFoundException;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupsTargetsService;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("private/backups")
public class BackupsPrivateApiController extends SuperPrivateController {


    @Autowired
    private BackupsService backupsService;

    @Autowired
    private AgentsService agentsService;

    @Autowired
    private BackupsTargetsService backupsTargetsService;


    @GetMapping("/")
    @JsonView(Full.class)
    public Page<Backup> getBackups(@SortDefault(value = "startDate", direction = DESC) Pageable pageable,
                                   @RequestParam(value = "agent", required = false) String agentId,
                                   @RequestParam(value = "backupTarget", required = false) String targetId) throws AgentNotFoundException, BackupTargetNotFoundException {

        if (null != targetId) {
            AbstractBackupTarget backupTarget = backupsTargetsService.getBackupTarget(targetId);
            return backupsService.getBackups(backupTarget, pageable);
        }

        if (null != agentId) {
            Agent agent = agentsService.getAgent(agentId);
            return backupsService.getBackups(agent, pageable);
        }
        //all
        return backupsService.getBackups(pageable);
    }


}
