package ru.vyukov.bakapa.admin.controller.agents;

import org.bakapa.domain.BackupTargetType;
import org.bakapa.dto.agent.AgentDTO;
import org.bakapa.dto.backups.AbstractBackupTargetDTO;
import org.bakapa.dto.backups.DatabaseBackupTargetDTO;
import org.bakapa.dto.backups.DirectoryBackupTargetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vyukov.bakapa.admin.controller.SuperUIController;
import ru.vyukov.bakapa.admin.service.agents.AgentsApiClient;
import ru.vyukov.bakapa.admin.service.agents.BackupsTargetsApiClient;
import ru.vyukov.bakapa.admin.service.agents.backups.BackupTargetsFactory;

@Controller
@RequestMapping("/agents/{agentId}/targets/{backupTargetId}")
public class EditBackupTargetController extends SuperUIController {

    private final static String NEW_ID = "new";

    @Autowired
    private BackupTargetsFactory backupTargetsFactory;


    @Autowired
    private AgentsApiClient agentsApiClient;

    @Autowired
    private BackupsTargetsApiClient backupsTargetsApiClient;


    @ModelAttribute
    public void model(@PathVariable("agentId") String agentId, Model model) {
        model.addAttribute("databasesTargetTypes", BackupTargetType.databases());
        model.addAttribute("filesystemTargetTypes", BackupTargetType.filesystem());
        model.addAttribute("agent", agentsApiClient.getAgent(agentId));
    }

    @GetMapping("/edit/database")
    public String editDatabase(Model model, @ModelAttribute("agent") AgentDTO agent) {
        model.addAttribute("backupTarget", backupTargetsFactory.newInstance(agent, BackupTargetType.MYSQL));
        return "backups/edit-database";
    }

    @GetMapping("/edit/filesystem")
    public String editFilesystem(Model model, @ModelAttribute("agent") AgentDTO agent) {
        model.addAttribute("backupTarget", backupTargetsFactory.newInstance(agent, BackupTargetType.FILESYSTEM));
        return "backups/edit-filesystem";
    }


    @PostMapping("/edit/filesystem")
    public String save(
            @ModelAttribute("agent") AgentDTO agent,
            @Validated @ModelAttribute("backupTarget") DirectoryBackupTargetDTO directoryBackupTargetDTO, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) {
        if (createOrUpdate(agent, directoryBackupTargetDTO, bindingResult, model, redirectAttributes)) {
            return AgentsController.redirect();
        }
        return "backups/edit-filesystem";
    }


    @PostMapping("/edit/database")
    public String save(
            @ModelAttribute("agent") AgentDTO agent,
            @Validated @ModelAttribute("backupTarget") DatabaseBackupTargetDTO databaseBackupTargetDTO, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) {
        if (createOrUpdate(agent, databaseBackupTargetDTO, bindingResult, model, redirectAttributes)) {
            return AgentsController.redirect();
        }
        return "backups/edit-database";
    }


    private boolean createOrUpdate(@ModelAttribute("agent") AgentDTO agent, AbstractBackupTargetDTO backupTargetDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            dangerMessage(model, bindingResult);
            return false;
        } else {
            if (isNewId(backupTargetDTO)) {
                backupTargetDTO.setBackupTargetId(null);
            }
            backupsTargetsApiClient.updateBackupTarget(agent.getAgentId(), backupTargetDTO);
            successMessage(redirectAttributes, "Successfully saved for agent " + agent.getAgentId());
            return true;
        }
    }

    private boolean isNewId(AbstractBackupTargetDTO abstractBackupTargetDTO) {
        return NEW_ID.equals(abstractBackupTargetDTO.getBackupTargetId());
    }


}
