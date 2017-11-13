package ru.vyukov.bakapa.admin.controller.backups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class BackupsController {

    @Autowired
    private AgentsApiClient agentsApiClient;

    @Autowired
    private BackupsApiClient backupsApiClient;

    @Autowired
    private BackupsTargetsApiClient targetsApiClient;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ModelAttribute
    private void model(Model model, @RequestParam(value = "agent", required = false) String anget) {
        model.addAttribute("agents", agentsApiClient.getAgents());

        if (null != anget) {
            model.addAttribute("targets", targetsApiClient.getBackupsTargets(anget));
        }
    }

    @GetMapping("/")
    public String allBackups(Model model, @ModelAttribute("filter") Filter filter, Pageable pageable) {
        Page<BackupDTO> backups = backupsApiClient.getBackups(pageable,filter);
        model.addAttribute("backups", backups);
        return "backups/backups";
    }


}
