package ru.vyukov.bakapa.agent.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import ru.vyukov.bakapa.agent.service.backup.BackupServiceConfig;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BackupServiceConfigTest {

    @InjectMocks
    private BackupServiceConfig backupServiceConfig;

    @Test
    public void testGetBackupPartitionSize() throws Exception {
        backupServiceConfig.setBackupPartitionSize("2MB");
        assertEquals(2 * 1024 * 1024, backupServiceConfig.getBackupPartitionSizeInBytes());

        //
        backupServiceConfig.setBackupPartitionSize("2GB");
        assertEquals(2L * 1024 * 1024 * 1024, backupServiceConfig.getBackupPartitionSizeInBytes());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBackupPartitionSizeIllegalFormat() throws Exception {
        backupServiceConfig.setBackupPartitionSize("GB");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBackupPartitionSizeIllegalFormat1() throws Exception {
        backupServiceConfig.setBackupPartitionSize("12.2GB");
    }

}
