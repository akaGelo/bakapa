package ru.vyukov.bakapa.controller.controller.priv;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.controller.domain.backup.Backup;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.backups.BackupsService;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupTargetNotFoundException;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupsTargetsService;

import java.util.Collections;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/private/targets/{backupTargetId}/backups")
public class BackupsOnTargetsPrivateApiController extends SuperPrivateController {


    @Autowired
    private BackupsTargetsService backupsTargetsService;

    @Autowired
    private BackupsService backupsService;

    @ModelAttribute
    public void model(
            @PathVariable("backupTargetId") String backupTargetId,
            Model model) throws BackupTargetNotFoundException {
        model.addAttribute("backupTarget", backupsTargetsService.getBackupTarget(backupTargetId));
    }


    @GetMapping("/backups/")
    public Page<Backup> getBackups(@ModelAttribute AbstractBackupTarget backupTarget,
                                   @SortDefault(value = "startTimestamp", direction = DESC) Pageable pageable) {

        return backupsService.getBackups(backupTarget,pageable);
    }


}
