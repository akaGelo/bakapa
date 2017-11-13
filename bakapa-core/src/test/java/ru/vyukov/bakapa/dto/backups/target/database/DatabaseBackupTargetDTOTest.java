package ru.vyukov.bakapa.dto.backups.target.database;

import org.junit.Test;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupTargetDTO;

public class DatabaseBackupTargetDTOTest {


    @Test
    public void testNotNullParamsExampleBuilders() throws NullPointerException {//(NPE check in lombok code)
        DatabaseBackupTargetDTO.localhostMongoDb();
        DatabaseBackupTargetDTO.localhostMysql();
        DatabaseBackupTargetDTO.localhostPostgresql();
    }

}