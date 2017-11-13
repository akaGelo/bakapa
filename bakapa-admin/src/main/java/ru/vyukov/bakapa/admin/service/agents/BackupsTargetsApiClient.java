package ru.vyukov.bakapa.admin.service.agents;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.dto.backups.target.BackupTargetAndInfoDTO;
import ru.vyukov.bakapa.dto.backups.target.SummaryBackupTargetDTO;

import java.util.List;

@FeignClient(value = "bakapa-controller")
@RequestMapping("/private/agents/{agentId}/targets")
public interface BackupsTargetsApiClient {


    @GetMapping("/")
    public List<BackupTargetAndInfoDTO> getBackupsTargets(@PathVariable("agentId") String agentId);

    @PostMapping("/")
    public void updateBackupTarget(@PathVariable("agentId") String agentId, @RequestBody SummaryBackupTargetDTO backupTarget);

    @GetMapping("/{backupTargetId}/")
    SummaryBackupTargetDTO getBackupTarget(@PathVariable("agentId") String agentId, @PathVariable("backupTargetId") String backupTargetId);
}
