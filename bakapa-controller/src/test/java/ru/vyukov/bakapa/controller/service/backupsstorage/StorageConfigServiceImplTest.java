package ru.vyukov.bakapa.controller.service.backupsstorage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import ru.vyukov.bakapa.controller.domain.config.StorageConfig;
import ru.vyukov.bakapa.controller.repo.ConfigsRepository;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StorageConfigServiceImplTest {
    @Mock
    private ConfigsRepository configsRepository;

    @InjectMocks
    private BackupsStorageServiceImpl underTest;

    @Test
    public void getStorage() throws Exception {

        StorageConfig storageConfig = StorageConfig.defaultConfig();

        underTest.update(storageConfig);

        verify(configsRepository).save(storageConfig);

    }

}