package ru.vyukov.bakapa.dto.backups.target;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.FilesystemBackupTargetDTO;

/**
 * Summary  view for backup target
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "targetType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FilesystemBackupTargetDTO.class, name = "FILESYSTEM"),
        //
        @JsonSubTypes.Type(value = DatabaseBackupTargetDTO.class, name = "MYSQL"),
        @JsonSubTypes.Type(value = DatabaseBackupTargetDTO.class, name = "MONGODB"),
        @JsonSubTypes.Type(value = DatabaseBackupTargetDTO.class, name = "POSTGRESQL")
})
public interface SummaryBackupTargetDTO extends BackupTarget {


}