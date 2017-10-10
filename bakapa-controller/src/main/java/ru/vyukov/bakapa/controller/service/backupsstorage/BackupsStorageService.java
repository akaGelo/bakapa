package ru.vyukov.bakapa.controller.service.backupsstorage;

import ru.vyukov.bakapa.controller.domain.BackupsStorage;

import java.util.List;

public interface BackupsStorageService {


    List<BackupsStorage> getAllStorages();

    BackupsStorage getStorage(String storageId);

    void delete(BackupsStorage backupsStorage);

    void create(BackupsStorage backupsStorage);

    void update(BackupsStorage backupsStorage);
}
