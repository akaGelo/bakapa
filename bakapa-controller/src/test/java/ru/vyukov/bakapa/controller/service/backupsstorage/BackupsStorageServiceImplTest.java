package ru.vyukov.bakapa.controller.service.backupsstorage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.vyukov.bakapa.controller.domain.BackupsStorage;
import ru.vyukov.bakapa.controller.repo.ConfigsRepository;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BackupsStorageServiceImplTest {
    @Mock
    private ConfigsRepository configsRepository;

    @InjectMocks
    private BackupsStorageServiceImpl underTest;

    @Test
    public void getStorage() throws Exception {

        BackupsStorage backupsStorage = BackupsStorage.defaultConfig();

        underTest.update(backupsStorage);

        verify(configsRepository).save(backupsStorage);

    }

}