package ru.vyukov.bakapa.admin.service.agents;

import org.bakapa.dto.backups.AbstractBackupTargetDTO;
import org.bakapa.dto.backups.BackupTargetAndInfoDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "bakapa-controller")
@RequestMapping("/private/agents/{agentId}/targets")
public interface BackupsTargetsApiClient {


    @GetMapping("/")
    public List<BackupTargetAndInfoDTO> getBackupsTargets(@PathVariable("agentId") String agentId);

    @PostMapping("/")
    public void updateBackupTarget(@PathVariable("agentId") String agentId, @RequestBody  AbstractBackupTargetDTO backupTarget);

    @GetMapping("/{backupTargetId}/")
    AbstractBackupTargetDTO getBackupTarget(@PathVariable("agentId") String agentId, @PathVariable("backupTargetId")  String backupTargetId);
}
