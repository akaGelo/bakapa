package ru.vyukov.bakapa.admin.service.agents.backups;

import org.bakapa.domain.BackupTargetType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BackupTargetsFactoryTest {

    @InjectMocks
    private BackupTargetsFactoryImpl underTest;

    @Test
    public void newInstance() throws Exception {
        for (BackupTargetType type : BackupTargetType.values()) {
            underTest.newInstance(type);
        }
    }

}