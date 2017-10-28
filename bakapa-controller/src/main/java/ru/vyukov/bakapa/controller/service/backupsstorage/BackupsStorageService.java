package ru.vyukov.bakapa.controller.service.backupsstorage;

import ru.vyukov.bakapa.controller.domain.config.BackupsStorage;

public interface BackupsStorageService {


    BackupsStorage getStorage();

    void update(BackupsStorage backupsStorage);
}
