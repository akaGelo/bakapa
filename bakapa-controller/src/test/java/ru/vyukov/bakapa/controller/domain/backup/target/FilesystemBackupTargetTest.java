package ru.vyukov.bakapa.controller.domain.backup.target;

import org.junit.Test;
import ru.vyukov.bakapa.controller.domain.backup.target.database.DatabaseBackupTarget;
import ru.vyukov.bakapa.domain.BackupTargetType;

import static org.junit.Assert.*;
import static ru.vyukov.bakapa.dto.backups.target.BackupTarget.EVERY_DAY_CRON_TRIGGER;

public class FilesystemBackupTargetTest extends AgentChangeTest {

    @Test
    public void agentImmutableBuilder() throws Exception {

        FilesystemBackupTarget filesystemBackupTarget = FilesystemBackupTarget.demo(agentOne, 1);

        FilesystemBackupTarget modifiedTarget = filesystemBackupTarget.agent(agentTwo);

        assertFalse(filesystemBackupTarget == modifiedTarget);//link equals
        assertEquals(agentTwo, modifiedTarget.getAgent());

        assertNotNull(modifiedTarget.getTargetType());
        assertNotNull(modifiedTarget.getBackupTargetId());
        assertNotNull(modifiedTarget.getPath());

    }


    @Test
    public void testEquals() {
        FilesystemBackupTarget bt1 = FilesystemBackupTarget.demo(agentOne, 1);

        FilesystemBackupTarget bt2 = FilesystemBackupTarget.demo(agentOne, 1);
        assertEquals(bt1, bt2);

        DatabaseBackupTarget dbBt = new DatabaseBackupTarget(bt1.getBackupTargetId(), agentOne, BackupTargetType.MYSQL, EVERY_DAY_CRON_TRIGGER, null, null, null);
        assertFalse(bt1.equals(dbBt));
    }

}