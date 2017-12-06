package ru.vyukov.bakapa.agent.service.backup;

import org.junit.Test;
import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.dto.backups.target.SummaryBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.FilesystemBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.task.BackupTaskDTO;
import ru.vyukov.bakapa.dump.DumpUtilWrapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static ru.vyukov.bakapa.domain.BackupTargetType.FILESYSTEM;

/**
 * @author Oleg Vyukov
 */
public class DumpUtilFactoryImplTest {

    @Test
    public void create() throws Exception {
        DumpUtilFactory dumpUtilFactory = new DumpUtilFactoryImpl();
        for (BackupTargetType backupTargetType : BackupTargetType.values()) {
            BackupTaskDTO backupTask = null;

            if (backupTargetType.isDatabase()) {
                backupTask = new BackupTaskDTO("test", testBackupDatabaseTarget(backupTargetType));
            } else if (backupTargetType.isFilesystem()) {
                backupTask = new BackupTaskDTO("test", testBackupFilesystemTarget());
            } else {
                fail("Logic error");
            }

            DumpUtilWrapper dumpUtilWrapper = dumpUtilFactory.create(backupTask);
            assertNotNull(dumpUtilWrapper);
        }
    }

    private SummaryBackupTargetDTO testBackupDatabaseTarget(BackupTargetType backupTargetType) {
        return new DatabaseBackupTargetDTO() {
            @Override
            public BackupTargetType getTargetType() {
                return backupTargetType;
            }
        };
    }


    private SummaryBackupTargetDTO testBackupFilesystemTarget() {
        return new FilesystemBackupTargetDTO() {
            @Override
            public BackupTargetType getTargetType() {
                return FILESYSTEM;
            }

            @Override
            public String getPath() {
                return "/tmp";
            }
        };
    }
}