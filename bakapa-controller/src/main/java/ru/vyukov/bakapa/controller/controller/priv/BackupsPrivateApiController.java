package ru.vyukov.bakapa.controller.controller.priv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.controller.domain.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.domain.Backup;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.backups.BackupTargetNotFoundException;
import ru.vyukov.bakapa.controller.service.backups.BackupsTargetsService;

import java.util.List;

@RestController
@RequestMapping("/targets/{backupTargetId}/backups")
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

        return null;
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
