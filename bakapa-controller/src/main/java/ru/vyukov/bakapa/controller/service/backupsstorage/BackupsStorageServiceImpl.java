package ru.vyukov.bakapa.controller.service.backupsstorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vyukov.bakapa.controller.domain.BackupsStorage;
import ru.vyukov.bakapa.controller.repo.BackupsStorageRepository;

import java.util.List;

@Service
public class BackupsStorageServiceImpl implements  BackupsStorageService{

    private BackupsStorageRepository backupsStorageRepository;

    @Autowired
    public BackupsStorageServiceImpl(BackupsStorageRepository backupsStorageRepository) {
        this.backupsStorageRepository = backupsStorageRepository;
    }


    @Override
    public List<BackupsStorage> getAllStorages(){
        return backupsStorageRepository.findAll();
    }

    @Override
    public  BackupsStorage getStorage(String storageId){
        return backupsStorageRepository.findOne(storageId);
    }

    @Override
    public void delete(BackupsStorage backupsStorage){
        backupsStorageRepository.delete(backupsStorage);
    }

    @Override
    public void create(BackupsStorage backupsStorage){
        if (null!=backupsStorage.getStorageId()){
            throw new IllegalArgumentException("id should be null");
        }
        backupsStorageRepository.save(backupsStorage);
    }


    @Override
    public void update(BackupsStorage backupsStorage){
        if (null==backupsStorage.getStorageId()){
            throw new IllegalArgumentException("id should not be null");
        }
        backupsStorageRepository.save(backupsStorage);
    }

}
