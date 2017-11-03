package org.bakapa.dto.backups.database;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseBackupTargetDTOTest {


    @Test
    public void testNotNullParamsExampleBuilders() throws NullPointerException {//(NPE check in lombok code)
        DatabaseBackupTargetDTO.localhostMongoDb();
        DatabaseBackupTargetDTO.localhostMysql();
        DatabaseBackupTargetDTO.localhostPostgresql();
    }

}