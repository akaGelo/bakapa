package ru.vyukov.bakapa.admin.controller.agents;

import org.bakapa.dto.agent.AgentDTO;
import org.bakapa.dto.backups.BackupTargetAndInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vyukov.bakapa.admin.controller.SuperUIController;
import ru.vyukov.bakapa.admin.service.agents.AgentsApiClient;
import ru.vyukov.bakapa.admin.service.agents.BackupsTargetsApiClient;

import java.util.List;

@Controller
@RequestMapping("/agents/{agentId}/")
public class BackupTargetsOnAgentController extends SuperUIController {

    @Autowired
    private AgentsApiClient agentsApiClient;

    @Autowired
    private BackupsTargetsApiClient backupsTargetsApiClient;

    @ModelAttribute("agent")
    public AgentDTO agent(@PathVariable("agentId") String agentId) {
        return agentsApiClient.getAgent(agentId);
    }


    @GetMapping("/targets")
    public String agents(@ModelAttribute("agent") AgentDTO agent, Model model) {
        List<BackupTargetAndInfoDTO> backupsTargets = backupsTargetsApiClient.getBackupsTargets(agent.getAgentId());

        model.addAttribute("backupsTargets", backupsTargets);

        return "agents/backups-targets-agent";
    }


}
