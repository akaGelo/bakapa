package ru.vyukov.bakapa.admin.controller.backups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.admin.service.agents.AgentsApiClient;
import ru.vyukov.bakapa.admin.service.agents.BackupsApiClient;
import ru.vyukov.bakapa.admin.service.agents.BackupsTargetsApiClient;
import ru.vyukov.bakapa.dto.backups.BackupDTO;

@Controller
@RequestMapping("/backups")
public class BackupController {


    @Autowired
    private BackupsApiClient backupsApiClient;


    @GetMapping("/{backupId}/")
    public String backup(Model model) {

        return "backups/backup";
    }


}