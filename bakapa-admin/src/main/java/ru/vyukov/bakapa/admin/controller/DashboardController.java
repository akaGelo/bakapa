package ru.vyukov.bakapa.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vyukov.bakapa.admin.controller.backups.Filter;
import ru.vyukov.bakapa.admin.service.agents.BackupsApiClient;
import ru.vyukov.bakapa.admin.service.agents.utils.Page;
import ru.vyukov.bakapa.dto.backups.BackupDTO;

@Controller
public class DashboardController {


    @Autowired
    private BackupsApiClient backupsApiClient;


    @ModelAttribute("backups")
    public Page<BackupDTO> backups() {
        Pageable pageable = new PageRequest(0, 20);
        Filter filter = Filter.empty();
        return backupsApiClient.getBackups(pageable, filter);
    }

    @RequestMapping("/")
    public String dashboard(Model model) {

        return "dashboard";
    }
}
