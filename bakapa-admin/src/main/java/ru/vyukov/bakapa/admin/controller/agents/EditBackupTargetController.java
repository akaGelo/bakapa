package ru.vyukov.bakapa.admin.controller.agents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vyukov.bakapa.admin.controller.SuperUIController;
import ru.vyukov.bakapa.admin.controller.agents.pojo.CronExpressionValidateResult;
import ru.vyukov.bakapa.admin.service.agents.AgentsApiClient;
import ru.vyukov.bakapa.admin.service.agents.BackupsTargetsApiClient;
import ru.vyukov.bakapa.admin.service.agents.backups.BackupTargetsFactory;
import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.dto.agent.AgentDTO;
import ru.vyukov.bakapa.dto.backups.target.SummaryBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.FilesystemBackupTargetDTO;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;
import java.util.function.Supplier;

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
    public void model(@PathVariable("agentId") String agentId, @PathVariable("backupTargetId") String backupTargetId, Model model) {
        model.addAttribute("databasesTargetTypes", BackupTargetType.databases());
        model.addAttribute("filesystemTargetTypes", BackupTargetType.filesystem());
        model.addAttribute("agent", agentsApiClient.getAgent(agentId));

        if (!isNewId(backupTargetId)) {
            SummaryBackupTargetDTO backupTarget = backupsTargetsApiClient.getBackupTarget(agentId, backupTargetId);
            model.addAttribute("backupTarget", backupTarget);
        }
    }


    @GetMapping("/edit/")
    public String edit(Model model, @ModelAttribute("agent") AgentDTO agent, @ModelAttribute("backupTarget") SummaryBackupTargetDTO abstractBackupTarget) {
        BackupTargetType targetType = abstractBackupTarget.getTargetType();
        String redirectUrl = "redirect:/agents/{agentId}/targets/{backupTargetId}/edit/";
        switch (targetType) {
            case MONGODB:
            case MYSQL:
            case POSTGRESQL:
                return redirectUrl + "database";
            case FILESYSTEM:
                return redirectUrl + "filesystem";
            default:
                throw new IllegalArgumentException("Backup " + targetType + " edit not implemented");
        }
    }


    @GetMapping("/edit/database")
    public String editDatabase(Model model, @ModelAttribute("agent") AgentDTO agent) {
        addIfAbsent(model, () -> backupTargetsFactory.newInstance(agent, BackupTargetType.MYSQL));
        return "backups/edit-database";
    }


    @GetMapping("/edit/filesystem")
    public String editFilesystem(Model model, @ModelAttribute("agent") AgentDTO agent) {
        addIfAbsent(model, () -> backupTargetsFactory.newInstance(agent, BackupTargetType.FILESYSTEM));
        return "backups/edit-filesystem";
    }

    private void addIfAbsent(Model model, Supplier<SummaryBackupTargetDTO> supplier) {
        if (!model.containsAttribute("backupTarget")) {
            model.addAttribute("backupTarget", supplier.get());
        }
    }


    @PostMapping("/edit/filesystem")
    public String save(
            @ModelAttribute("agent") AgentDTO agent,
            @Validated @ModelAttribute("backupTarget") FilesystemBackupTargetDTO filesystemBackupTargetDTO, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) {
        if (createOrUpdate(agent, filesystemBackupTargetDTO, bindingResult, model, redirectAttributes)) {
            return BackupTargetsOnAgentController.redirectTargets();
        }
        return "backups/edit-filesystem";
    }


    @PostMapping("/edit/database")
    public String save(
            @ModelAttribute("agent") AgentDTO agent,
            @Validated @ModelAttribute("backupTarget") DatabaseBackupTargetDTO databaseBackupTargetDTO, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) {
        if (createOrUpdate(agent, databaseBackupTargetDTO, bindingResult, model, redirectAttributes)) {
            return BackupTargetsOnAgentController.redirectTargets();
        }
        return "backups/edit-database";
    }


    private boolean createOrUpdate(@ModelAttribute("agent") AgentDTO agent, SummaryBackupTargetDTO backupTargetDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
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

    private boolean isNewId(SummaryBackupTargetDTO abstractBackupTargetDTO) {
        return isNewId(abstractBackupTargetDTO.getBackupTargetId());
    }

    private boolean isNewId(String backupTargetId) {
        return NEW_ID.equals(backupTargetId);
    }


    @ResponseBody
    @GetMapping("/edit/cron/validation")
    public CronExpressionValidateResult validate(@RequestParam String expression, @RequestParam int timeZoneOffset) {
        try {
            ZoneOffset zoneOffset = ZoneOffset.ofHours(timeZoneOffset);
            TimeZone timeZone = TimeZone.getTimeZone(zoneOffset.getId());
            CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(expression);
            Date nextDate = cronSequenceGenerator.next(new Date());
            return CronExpressionValidateResult.validExpression(expression, nextDate);
        } catch (Exception e) {
            return CronExpressionValidateResult.wrongExpression(expression);
        }
    }

}
