package ru.vyukov.bakapa.admin.service.agents;

import org.bakapa.dto.agent.AgentAndCredentialsDTO;
import org.bakapa.dto.agent.AgentDTO;
import org.bakapa.dto.backups.AbstractBackupTargetDTO;
import org.bakapa.dto.backups.BackupsStorageDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "bakapa-controller")
@RequestMapping("/private")
public interface StorageApiClient {


    @GetMapping("/storage/")
    public BackupsStorageDTO getStorageConfig();

    @PostMapping("/storage/")
    public void saveStorageConfig(@RequestBody BackupsStorageDTO storage);
}
