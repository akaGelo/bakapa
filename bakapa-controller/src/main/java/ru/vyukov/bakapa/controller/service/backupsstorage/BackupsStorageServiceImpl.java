package ru.vyukov.bakapa.controller.service.backupsstorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vyukov.bakapa.controller.domain.BackupsStorage;
import ru.vyukov.bakapa.controller.repo.ConfigsRepository;

import javax.annotation.PostConstruct;

@Service
public class BackupsStorageServiceImpl implements BackupsStorageService {

    private ConfigsRepository configsRepository;

    @Autowired
    public BackupsStorageServiceImpl(ConfigsRepository configsRepository) {
        this.configsRepository = configsRepository;
    }

    @PostConstruct
    synchronized public void init() {
        if (null == getStorage()) {
            BackupsStorage backupsStorage = BackupsStorage.defaultConfig();
            configsRepository.save(backupsStorage);
        }
    }

    @Override
    public BackupsStorage getStorage() {
        BackupsStorage backupsStorage = configsRepository.findOneBackupStorageBy(BackupsStorage.INSTANCE_ID);
        return backupsStorage;
    }


    @Override
    public void update(BackupsStorage backupsStorage) {
        configsRepository.save(backupsStorage);
    }

}
