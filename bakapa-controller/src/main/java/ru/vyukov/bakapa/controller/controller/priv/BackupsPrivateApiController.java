package ru.vyukov.bakapa.controller.controller.priv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.controller.domain.Backup;
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupTargetNotFoundException;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupsTargetsService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/private/targets/{backupTargetId}/backups")
public class BackupsPrivateApiController extends SuperPrivateController {


    @Autowired
    private BackupsTargetsService backupsTargetsService;

    @Autowired
    private AgentsService agentsService;


    @ModelAttribute
    public void model(
            @PathVariable("backupTargetId") String backupTargetId,
            Model model) throws BackupTargetNotFoundException {
        model.addAttribute("backupTarget", backupsTargetsService.getBackupTarget(backupTargetId));
    }



    @GetMapping("/backups/")
    public List<Backup> getBackups(@ModelAttribute AbstractBackupTarget backupTarget) {

        return Collections.emptyList();
    }

    @GetMapping("/backups/{backupId}/")
    public Backup getBackup(@ModelAttribute AbstractBackupTarget backupTarget,
                                   @PathVariable("backupId") String backupId) {

        return  null;
    }


    @DeleteMapping("/backups/{backupId}/")
    public Backup deleteBackup(@ModelAttribute AbstractBackupTarget backupTarget,
                            @PathVariable("backupId") String backupId) {

        return  null;
    }

}
