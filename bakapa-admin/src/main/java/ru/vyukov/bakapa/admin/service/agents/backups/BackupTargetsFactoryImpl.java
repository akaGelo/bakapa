package ru.vyukov.bakapa.admin.service.agents.backups;

import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.dto.agent.AgentDTO;
import ru.vyukov.bakapa.dto.backups.AbstractBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.database.DatabaseBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.FilesystemBackupTargetDTO;
import org.springframework.stereotype.Component;

@Component
public class BackupTargetsFactoryImpl implements BackupTargetsFactory {

    @Override
    public AbstractBackupTargetDTO newInstance(AgentDTO agent, BackupTargetType type) {
        switch (type) {
            case MYSQL:
                return DatabaseBackupTargetDTO.localhostMysql();
            case POSTGRESQL:
                return DatabaseBackupTargetDTO.localhostPostgresql();
            case MONGODB:
                return DatabaseBackupTargetDTO.localhostMongoDb();
            case FILESYSTEM:
                return FilesystemBackupTargetDTO.defaultExample();
            default:
                throw new IllegalStateException(type + " not implemented");
        }
    }
}
