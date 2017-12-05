package ru.vyukov.bakapa.controller.service.scheduler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.backups.BackupsService;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupsTargetsService;
import ru.vyukov.bakapa.controller.service.scheduler.executor.BackupTaskExecutor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BackupSchedulerServiceImplTest {


    @Mock
    private BackupsTargetsService backupsTargetsService;
    @Mock
    private BackupsService backupsService;
    @Mock
    private AgentsService agentsService;
    @Mock
    private BackupTaskExecutor backupTaskExecutor;
    @Mock
    private Map<AbstractBackupTarget, ScheduledFuture<?>> abstractBackupTargetScheduledFutureMap;
    @InjectMocks
    private BackupSchedulerServiceImpl underTest;

    final private AbstractBackupTarget abstractBackupTarget = AbstractBackupTarget.demo();

    @Test
    public void testInit() throws Exception {

        when(backupTaskExecutor.schedule(any(Runnable.class), any(String.class))).thenReturn(mock(ScheduledFuture.class));

        when(backupsTargetsService.getBackupsTargets(any(Pageable.class))).thenAnswer(
                demoTargets(30)
        );

        //
        underTest.init();
    }

    @Test(expected = IllegalStateException.class)
    public void testDuplicateException() throws Exception {
        when(backupTaskExecutor.schedule(any(Runnable.class), any(String.class))).thenReturn(mock(ScheduledFuture.class));

        when(backupsTargetsService.getBackupsTargets(any(Pageable.class))).thenAnswer(
                demoWrongTargets(30)
        );

        //
        underTest.init();

        verify(backupTaskExecutor, only()).shutdownNow();
    }


    @Test
    public void testUpdate() throws Exception {
        testInit();

        ScheduledFuture mockFuture = mock(ScheduledFuture.class);
        when(backupTaskExecutor.schedule(any(Runnable.class), eq(abstractBackupTarget.getTrigger()))).thenReturn(mockFuture);

        ScheduledFuture<?> future = underTest.update(abstractBackupTarget);

        assertEquals(mockFuture, future);
    }


    private Answer<Page<AbstractBackupTarget>> demoTargets(int total) {
        return (inv) -> {
            Pageable pageable = inv.getArgument(0);
            int size = pageable.getPageSize();
            int start = pageable.getPageNumber() * size;
            Page<AbstractBackupTarget> page = new PageImpl<AbstractBackupTarget>(demoTargets(start, size), pageable, total);
            return page;
        };
    }

    private Answer<Page<AbstractBackupTarget>> demoWrongTargets(int total) {
        return (inv) -> {
            Pageable pageable = inv.getArgument(0);
            //for duplicate exception test
            Page<AbstractBackupTarget> page = new PageImpl<AbstractBackupTarget>(demoTargets(0, 10), pageable, total);
            return page;
        };
    }

    private List<AbstractBackupTarget> demoTargets(int start, int size) {
        return range(start, size).mapToObj(i -> AbstractBackupTarget.demo(i)).collect(toList());
    }

}