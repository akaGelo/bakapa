package ru.vyukov.bakapa.controller.repo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vyukov.bakapa.controller.config.MongoDbConfig;
import ru.vyukov.bakapa.controller.domain.BackupsStorage;
import ru.vyukov.bakapa.controller.domain.DetectionConfig;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@Import({MongoDbConfig.class, ValidationAutoConfiguration.class})
@DataMongoTest
public class ConfigsRepositoryTest {


    @Autowired
    private ConfigsRepository configsRepository;

    @Before
    public void setUp() throws Exception {
        configsRepository.deleteAll();
    }

    @Test
    public void testSaveAndRead() {
        BackupsStorage backupsStorage = BackupsStorage.defaultConfig();
        BackupsStorage returnedStorage = configsRepository.save(backupsStorage);
        assertEquals(backupsStorage, returnedStorage);


        configsRepository.save(backupsStorage);//test singleton save
        assertEquals(1, configsRepository.count());

        BackupsStorage readedStorage2 = configsRepository.findOneBackupStorageBy(BackupsStorage.INSTANCE_ID);
        assertEquals(backupsStorage, readedStorage2);
    }


    @Test
    public void testSaveAndReadManyClasses() {
        BackupsStorage backupsStorage = BackupsStorage.defaultConfig();
        BackupsStorage returnedStorage = configsRepository.save(backupsStorage);
        returnedStorage = configsRepository.findOneBackupStorageBy(BackupsStorage.INSTANCE_ID);
        assertEquals(backupsStorage, returnedStorage);


        DetectionConfig detectionConfig = DetectionConfig.defaultConfig();
        DetectionConfig returnedDetectionConfig = configsRepository.save(detectionConfig);

        returnedDetectionConfig = configsRepository.findOneDetectionConfigBy(DetectionConfig.INSTANCE_ID);
        assertEquals(detectionConfig, returnedDetectionConfig);

        //
        assertEquals(2, configsRepository.count());
    }
}