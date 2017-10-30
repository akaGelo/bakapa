package ru.vyukov.bakapa.admin.service.agents;

import org.bakapa.dto.backups.BackupsStorageDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "bakapa-controller")
@RequestMapping("/private")
public interface StorageApiClient {


    @GetMapping("/storage/")
    public BackupsStorageDTO getStorageConfig();

    @PostMapping("/storage/")
    public void saveStorageConfig(@RequestBody BackupsStorageDTO storage);
}
