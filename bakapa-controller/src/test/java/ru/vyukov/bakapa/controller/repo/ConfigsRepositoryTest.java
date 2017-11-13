package ru.vyukov.bakapa.controller.repo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vyukov.bakapa.controller.config.MongoDbConfig;
import ru.vyukov.bakapa.controller.domain.config.StorageConfig;
import ru.vyukov.bakapa.controller.domain.config.DetectionConfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;



public class ConfigsRepositoryTest extends  SuperRepoTest{


    @Autowired
    private ConfigsRepository configsRepository;

    @Before
    public void setUp() throws Exception {
        configsRepository.deleteAll();
    }

    @Test
    public void testSaveAndRead() {
        StorageConfig storageConfig = StorageConfig.defaultConfig();
        StorageConfig returnedStorage = configsRepository.save(storageConfig);
        assertEquals(storageConfig, returnedStorage);


        configsRepository.save(storageConfig);//test singleton save
        assertEquals(1, configsRepository.count());

        StorageConfig readedStorage2 = configsRepository.findOneBackupStorageById(StorageConfig.INSTANCE_ID);
        assertEquals(storageConfig, readedStorage2);
    }


    @Test
    public void testSaveAndReadManyClasses() {
        StorageConfig storageConfig = StorageConfig.defaultConfig();
        StorageConfig returnedStorage = configsRepository.save(storageConfig);
        returnedStorage = configsRepository.findOneBackupStorageById(StorageConfig.INSTANCE_ID);
        assertEquals(storageConfig, returnedStorage);


        DetectionConfig detectionConfig = DetectionConfig.defaultConfig();
        DetectionConfig returnedDetectionConfig = configsRepository.save(detectionConfig);

        returnedDetectionConfig = configsRepository.findOneDetectionConfigById(DetectionConfig.INSTANCE_ID);
        assertEquals(detectionConfig, returnedDetectionConfig);
        assertFalse(returnedDetectionConfig.getMongoPorts().isEmpty());

        //
        assertEquals(2, configsRepository.count());
    }
}