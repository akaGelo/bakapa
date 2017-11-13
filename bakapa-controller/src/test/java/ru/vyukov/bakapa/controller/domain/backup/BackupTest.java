package ru.vyukov.bakapa.controller.domain.backup;

import org.junit.Test;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;
import ru.vyukov.bakapa.domain.BackupState;

import static org.junit.Assert.*;
import static ru.vyukov.bakapa.domain.BackupState.INPROGRESS;

public class BackupTest {

    private Backup backup = Backup.newBackup(AbstractBackupTarget.demo());

    @Test
    public void isNotFinished() throws Exception {
        for (BackupState state : BackupState.values()) {
            backup.setState(state);

            if (state.equals(INPROGRESS)) {
                assertTrue(backup.isNotFinished());
            } else {
                assertFalse(backup.isNotFinished());
            }
        }
    }

}