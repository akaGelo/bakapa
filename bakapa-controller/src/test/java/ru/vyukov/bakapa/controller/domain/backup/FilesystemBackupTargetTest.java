package ru.vyukov.bakapa.controller.domain.backup;

import org.junit.Test;
import ru.vyukov.bakapa.controller.domain.backup.FilesystemBackupTarget;

import static org.junit.Assert.*;

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

}