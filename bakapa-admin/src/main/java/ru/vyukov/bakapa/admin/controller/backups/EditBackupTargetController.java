package ru.vyukov.bakapa.admin.controller.backups;

import org.bakapa.domain.BackupTargetType;
import org.bakapa.dto.backups.AbstractBackupTargetDTO;
import org.bakapa.dto.backups.DatabaseBackupTargetDTO;
import org.bakapa.dto.backups.DirectoryBackupTargetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.admin.controller.SuperUIController;
import ru.vyukov.bakapa.admin.service.agents.AgentsApiClient;
import ru.vyukov.bakapa.admin.service.agents.backups.BackupTargetsFactory;

import java.util.List;

@Controller
@RequestMapping("/backups/{backupId}")
public class EditBackupTargetController extends SuperUIController{

    private final static String NEW_ID = "new";

    @Autowired
    private BackupTargetsFactory backupTargetsFactory;

    @Autowired
    private AgentsApiClient agentsApiClient;


    @ModelAttribute
    public void model(Model model){
        model.addAttribute("databasesTargetTypes",BackupTargetType.databases());
        model.addAttribute("filesystemTargetTypes",BackupTargetType.filesystem());
        model.addAttribute("agents",agentsApiClient.getAgents());
    }

    @GetMapping("/edit/database")
    public String editDatabase(Model model) {
        model.addAttribute("backupTarget", backupTargetsFactory.newInstance(BackupTargetType.MYSQL));
        return "backups/edit-database";
    }

    @GetMapping("/edit/filesystem")
    public String editFilesystem(Model model) {
        model.addAttribute("backupTarget", backupTargetsFactory.newInstance(BackupTargetType.FILESYSTEM));
        return "backups/edit-filesystem";
    }


    @PostMapping("/edit/filesystem")
    public String save(@Validated @ModelAttribute("backupTarget") DirectoryBackupTargetDTO directoryBackupTargetDTO, BindingResult bindingResult) {

        return "backups/edit-filesystem";
    }

    @PostMapping("/edit/database")
    public String save(@Validated @ModelAttribute("backupTarget") DatabaseBackupTargetDTO databaseBackupTargetDTO, BindingResult bindingResult,Model model) {

        if (bindingResult.hasErrors()){
            dangerMessage(model,bindingResult);
        }else{
            //TODO send to controller
        }

        return "backups/edit-database";
    }


    private boolean isNewId(String backupId) {
        return NEW_ID.equals(backupId);
    }


}
