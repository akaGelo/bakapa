package ru.vyukov.bakapa.controller.service.backupsstorage;

import ru.vyukov.bakapa.controller.domain.BackupsStorage;

import java.util.List;

public interface BackupsStorageService {


    BackupsStorage getStorage();

    void update(BackupsStorage backupsStorage);
}
