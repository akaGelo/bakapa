package ru.vyukov.bakapa.controller.controller.priv.config;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.controller.controller.priv.SuperPrivateController;
import ru.vyukov.bakapa.controller.domain.View.Full;
import ru.vyukov.bakapa.controller.domain.config.StorageConfig;
import ru.vyukov.bakapa.controller.service.backupsstorage.BackupsStorageService;

@RestController
@RequestMapping("/private/config/storage")
public class StorageConfigPrivateApiController extends SuperPrivateController {

    @Autowired
    private BackupsStorageService backupsStorageService;

    @JsonView(Full.class)
    @GetMapping("/")
    public StorageConfig getStorageConfig() {
        return backupsStorageService.getStorage();
    }

    @PostMapping("/")
    public void saveStorageConfig(@Validated @RequestBody StorageConfig storage) {
        backupsStorageService.update(storage);
    }
}
