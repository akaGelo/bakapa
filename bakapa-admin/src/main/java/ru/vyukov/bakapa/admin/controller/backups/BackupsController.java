package ru.vyukov.bakapa.admin.controller.backups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vyukov.bakapa.admin.service.agents.AgentsApiClient;

@Controller
@RequestMapping("/backups")
public class BackupsController {

    @Autowired
    private AgentsApiClient agentsApiClient;

    @ModelAttribute
    private void model(Model model) {
        model.addAttribute("model",agentsApiClient.getAgents());
    }

    @GetMapping("/")
    public String allBackups() {

        return "backups/backups";
    }

    @GetMapping("/{agentId}/")
    public String backups(@PathVariable("agentId") String agentId, Model model) {


        return "backups/backups";
    }

}
