package ru.vyukov.bakapa.controller.service.backupsstorage;

import ru.vyukov.bakapa.controller.domain.config.StorageConfig;

public interface BackupsStorageService {


    StorageConfig getStorage();

    void update(StorageConfig storageConfig);
}
