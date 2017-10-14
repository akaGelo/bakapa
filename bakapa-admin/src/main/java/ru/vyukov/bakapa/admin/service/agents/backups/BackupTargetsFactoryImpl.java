package ru.vyukov.bakapa.admin.service.agents.backups;

import org.bakapa.domain.BackupTargetType;
import org.bakapa.dto.backups.AbstractBackupTargetDTO;
import org.bakapa.dto.backups.DatabaseBackupTargetDTO;
import org.bakapa.dto.backups.DirectoryBackupTargetDTO;
import org.springframework.stereotype.Component;

@Component
public class BackupTargetsFactoryImpl implements BackupTargetsFactory {

    @Override
    public AbstractBackupTargetDTO newInstance(BackupTargetType type) {
        switch (type) {
            case MYSQL:
                return DatabaseBackupTargetDTO.localhostMysql();
            case POSTGRESQL:
                return DatabaseBackupTargetDTO.localhostPostgresql();
            case MONGODB:
                return DatabaseBackupTargetDTO.localhostMongoDb();
            case FILESYSTEM:
                return new DirectoryBackupTargetDTO(null, "/etc/");
            default:
                throw new IllegalStateException(type + " not implemented");
        }
    }
}
