package ru.vyukov.bakapa.dto.backups.target;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.vyukov.bakapa.dto.agent.AgentDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.FullDatabaseBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.FullFilesystemBackupTargetDTO;

/**
 * Full view for backup target
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "targetType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FullFilesystemBackupTargetDTO.class, name = "FILESYSTEM"),
        //
        @JsonSubTypes.Type(value = FullDatabaseBackupTargetDTO.class, name = "MYSQL"),
        @JsonSubTypes.Type(value = FullDatabaseBackupTargetDTO.class, name = "MONGODB"),
        @JsonSubTypes.Type(value = FullDatabaseBackupTargetDTO.class, name = "POSTGRESQL")
})
public interface FullBackupTargetDTO extends BackupTarget {


    AgentDTO getAgent();
}
