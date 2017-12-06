package ru.vyukov.bakapa.agent.service.backup;

import org.springframework.stereotype.Component;
import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.dto.backups.target.SummaryBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.FilesystemBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.task.BackupTaskDTO;
import ru.vyukov.bakapa.dump.fs.DirectoryTarDump;
import ru.vyukov.bakapa.dump.DumpUtilWrapper;
import ru.vyukov.bakapa.dump.mongodb.MongoDbDump;
import ru.vyukov.bakapa.dump.mysql.MySqlDump;
import ru.vyukov.bakapa.dump.postgresql.PostgreSqlDump;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Oleg Vyukov
 */

@Component
public class DumpUtilFactoryImpl implements DumpUtilFactory {

    @Override
    public DumpUtilWrapper create(BackupTaskDTO backupTask) {
        SummaryBackupTargetDTO backupTarget = backupTask.getBackupTarget();
        BackupTargetType targetType = backupTarget.getTargetType();
        switch (targetType) {
            case MYSQL:
                return new MySqlDump(databaseBackupTargetDTO(backupTarget));
            case MONGODB:
                return new MongoDbDump(databaseBackupTargetDTO(backupTarget));
            case POSTGRESQL:
                return new PostgreSqlDump(databaseBackupTargetDTO(backupTarget));
            case FILESYSTEM:
                return new DirectoryTarDump(filesystemBackupTargetDTO(backupTarget));

        }
        throw new IllegalArgumentException("Not supported type " + targetType);
    }

    private DatabaseBackupTargetDTO databaseBackupTargetDTO(SummaryBackupTargetDTO backupTarget) {
        return (DatabaseBackupTargetDTO) backupTarget;
    }

    private FilesystemBackupTargetDTO filesystemBackupTargetDTO(SummaryBackupTargetDTO backupTarget) {
        return (FilesystemBackupTargetDTO) backupTarget;
    }
}
