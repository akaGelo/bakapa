package ru.vyukov.bakapa.admin.service.agents.backups;

import org.springframework.stereotype.Component;
import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.dto.agent.AgentDTO;
import ru.vyukov.bakapa.dto.backups.target.SummaryBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.FilesystemBackupTargetDTO;

@Component
public class BackupTargetsFactoryImpl implements BackupTargetsFactory {

    @Override
    public SummaryBackupTargetDTO newInstance(AgentDTO agent, BackupTargetType type) {
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
