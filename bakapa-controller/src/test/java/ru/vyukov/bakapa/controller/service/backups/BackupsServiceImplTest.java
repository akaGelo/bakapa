package ru.vyukov.bakapa.controller.service.backups;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.Backup;
import ru.vyukov.bakapa.controller.domain.backup.BackupLogItem;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.repo.BackupRepository;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupsTargetsService;

import javax.lang.model.util.Types;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BackupsServiceImplTest {

    private AbstractBackupTarget backTarget = AbstractBackupTarget.demo();

    private Agent agent = backTarget.getAgent();

    @Mock
    private BackupRepository backupRepository;
    @Mock
    private BackupsTargetsService backupsTargetsService;
    @Mock
    private BackupsLogsService backupsLogsService;

    @Captor
    private ArgumentCaptor<Backup> backupArgumentCaptor;


    @InjectMocks
    private BackupsServiceImpl underTest;


    @Test
    public void createNewBackup() throws Exception {
        when(backupRepository.save(any(Backup.class))).then(inv -> inv.getArgument(0));
        underTest.createNewBackup(backTarget);

        verify(backupRepository).findOneByBackupTargetOrderByStartTimestampAsc(backTarget);
        verify(backupRepository).save(backupArgumentCaptor.capture());

        assertNull(backupArgumentCaptor.getValue().getBackupId());

    }

    @Test
    public void createNewBackupFailPreviousNotCompleted() throws Exception {
        Backup backup = mock(Backup.class);

        when(backup.isNotFinished()).thenReturn(true);
        when(backupRepository.findOneByBackupTargetOrderByStartTimestampAsc(backTarget)).thenReturn(backup);
        when(backupRepository.save(any(Backup.class))).then(inv -> inv.getArgument(0));

        when(backupsLogsService.save(any(BackupLogItem.class))).then(inv -> inv.getArgument(0));

        try {
            underTest.createNewBackup(backTarget);
            fail("no throw new CreateBackupException()");
        } catch (CreateBackupException e) {
            ;
        }

        verify(backupRepository).save(backupArgumentCaptor.capture());
        verify(backupsLogsService).save(any(BackupLogItem.class));


        assertNull(backupArgumentCaptor.getValue().getBackupId());
    }

    @Test
    public void getBackups() throws Exception {
        Backup backup = mock(Backup.class);

        Pageable pageable = new PageRequest(0, 20);
        List<AbstractBackupTarget> backupTargets = Arrays.asList(backTarget);

        when(backupsTargetsService.getBackupsTargets(agent)).thenReturn(backupTargets);

        Page<Backup> backupsPage = asPage(backup);
        when(backupRepository.findAllByBackupTarget(backupTargets, pageable)).thenReturn(backupsPage);


        Page<Backup> returnedBackupsPage = underTest.getBackups(agent, pageable);

        assertEquals(backupsPage, returnedBackupsPage);
    }

    private Page<Backup> asPage(Backup backup) {
        return new PageImpl<Backup>(Arrays.asList(backup));
    }

}