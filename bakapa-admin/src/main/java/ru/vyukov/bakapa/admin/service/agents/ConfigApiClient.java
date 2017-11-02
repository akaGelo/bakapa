package ru.vyukov.bakapa.admin.service.agents;

import org.bakapa.dto.config.DetectionConfigDTO;
import org.bakapa.dto.config.StorageConfigDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "bakapa-controller")
@RequestMapping("/private/config")
public interface ConfigApiClient {

    @GetMapping("/storage/")
    public StorageConfigDTO getStorageConfig();

    @PostMapping("/storage/")
    public void saveStorageConfig(@RequestBody StorageConfigDTO storageConfig);


    @GetMapping("/detection/")
    public DetectionConfigDTO getDetectionConfig();

    @PostMapping("/detection/")
    public void saveDetectionConfig(@RequestBody DetectionConfigDTO detectionConfig);


}
