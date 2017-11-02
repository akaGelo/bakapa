package ru.vyukov.bakapa.controller.service.backupsstorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vyukov.bakapa.controller.domain.config.StorageConfig;
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
            StorageConfig storageConfig = StorageConfig.defaultConfig();
            configsRepository.save(storageConfig);
        }
    }

    @Override
    public StorageConfig getStorage() {
        StorageConfig storageConfig = configsRepository.findOneBackupStorageById(StorageConfig.INSTANCE_ID);
        return storageConfig;
    }


    @Override
    public void update(StorageConfig storageConfig) {
        configsRepository.save(storageConfig);
    }

}
