package ru.vyukov.bakapa.agent.service.backup;

import org.apache.commons.lang3.AnnotationUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import ru.vyukov.bakapa.dto.backups.task.BackupTaskDTO;
import ru.vyukov.bakapa.dto.backups.task.BackupTaskFinishEventDTO;
import ru.vyukov.bakapa.dto.backups.task.BackupTaskLogEventDTO;

import javax.annotation.PreDestroy;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Oleg Vyukov
 */
@RunWith(MockitoJUnitRunner.class)
public class BackupServiceImplTest {

    @Mock
    private BackupServiceConfig backupServiceConfig;
    @Mock
    private ExecutorService executorService;
    @Mock
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @InjectMocks
    private BackupServiceImpl underTest;


    @Test
    @Ignore()
    public void backup() throws Exception {
        BackupTaskDTO backTask = BackupTaskDTO.demo();
        underTest.backup(backTask);


        fail("not implemented");
    }


    @Test
    public void execute() {
        BackupTaskDTO backTask = BackupTaskDTO.demo();
        underTest.execute(backTask);

        verify(simpMessageSendingOperations).convertAndSend(eq(BackupTaskLogEventDTO.DESTINATION), any(BackupTaskLogEventDTO.class));
        verify(executorService).execute(any(Runnable.class));
    }


    @Test
    public void shutdown() throws Exception {
        Method shutdown = underTest.getClass().getMethod("shutdown");
        assertNotNull(shutdown.getAnnotation(PreDestroy.class));

        underTest.shutdown();
        verify(executorService).shutdown();
    }
}