package ru.vyukov.bakapa.controller.controller.priv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.service.agents.AgentNotFoundException;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupsTargetsService;

import java.util.List;

@RestController
@RequestMapping("/private/targets")
public class BackupTargetsPrivateApiController extends SuperPrivateController {


    @Autowired
    private BackupsTargetsService backupsTargetsService;

    @Autowired
    private AgentsService agentsService;



    @GetMapping("/")
    public List<AbstractBackupTarget> getAllBackupsTargets() throws AgentNotFoundException {
        return backupsTargetsService.getBackupsTargets();
    }


    @GetMapping("/{backupTargetId}/")
    public AbstractBackupTarget getBackupTarget(@PathVariable("backupTargetId") String backupTargetId){

        return  null;
    }

    @DeleteMapping("/{backupTargetId}/")
    public void deleteBackupTarget(@PathVariable("backupTargetId") String backupTargetId){

    }




}
