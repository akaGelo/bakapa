package ru.vyukov.bakapa.controller.controller.priv;

import com.fasterxml.jackson.annotation.JsonView;
import org.bakapa.dto.backups.BackupsStorageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.controller.domain.BackupsStorage;
import ru.vyukov.bakapa.controller.domain.View;
import ru.vyukov.bakapa.controller.domain.View.Full;
import ru.vyukov.bakapa.controller.service.backupsstorage.BackupsStorageService;

@RestController
@RequestMapping("/private")
public class StoragePrivateApiController extends  SuperPrivateController {

    @Autowired
    private BackupsStorageService backupsStorageService;

    @JsonView(Full.class)
    @GetMapping("/storage/")
    public BackupsStorage getStorageConfig() {
        return backupsStorageService.getStorage();
    }

    @PostMapping("/storage/")
    public void saveStorageConfig(@Validated @RequestBody BackupsStorage storage) {
        backupsStorageService.update(storage);
    }
}
