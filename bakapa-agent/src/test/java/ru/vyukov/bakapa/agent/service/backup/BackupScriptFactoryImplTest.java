package ru.vyukov.bakapa.agent.service.backup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.vyukov.bakapa.dto.backups.task.BackupTaskDTO;
import ru.vyukov.bakapa.dump.DumpUtilWrapper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Oleg Vyukov
 */
@RunWith(MockitoJUnitRunner.class)
public class BackupScriptFactoryImplTest {

    @Mock
    private DumpUtilFactory dumpUtilFactory;
    @Mock
    private BackupTaskDTO backupTask;
    @Mock
    private BackupServiceConfig backupServiceConfig;
    @Mock
    private Logger logger;
    @Mock
    private DumpUtilWrapper dumpUtil;


    @InjectMocks
    private BackupScriptFactoryImpl underTest;

    @Test
    public void create() throws Exception {
        when(dumpUtilFactory.create(backupTask)).thenReturn(dumpUtil);
        BackupScript backupScript = underTest.create(backupTask, backupServiceConfig, logger);
        assertNotNull(backupScript);
        verify(dumpUtilFactory).create(backupTask);
    }
}