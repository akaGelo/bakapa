package ru.vyukov.bakapa.controller.domain.backup.database;

import org.junit.Test;
import ru.vyukov.bakapa.controller.domain.backup.AgentChangeTest;

import static org.junit.Assert.*;

public class DatabaseBackupTargetTest extends AgentChangeTest{

    @Test
    public void agentImmutableBuilder() throws Exception {

        DatabaseBackupTarget databaseBackupTarget = DatabaseBackupTarget.demo(agentOne, 1);

        DatabaseBackupTarget modifiedTarget = databaseBackupTarget.agent(agentTwo);

        assertFalse(databaseBackupTarget == modifiedTarget);//link equals
        assertEquals(agentTwo, modifiedTarget.getAgent());

        assertNotNull(modifiedTarget.getTargetType());
        assertNotNull(modifiedTarget.getLocation());
        assertNotNull(modifiedTarget.getOptions());
        assertNotNull(modifiedTarget.getUserCredentials());
    }

}