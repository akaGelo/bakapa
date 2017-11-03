package org.bakapa.dto.backups;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bakapa.domain.BackupTargetType;
import org.bakapa.dto.backups.database.DatabaseBackupTargetDTO;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "targetType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FilesystemBackupTargetDTO.class, name = "FILESYSTEM"),
        //
        @JsonSubTypes.Type(value = DatabaseBackupTargetDTO.class, name = "MYSQL"),
        @JsonSubTypes.Type(value = DatabaseBackupTargetDTO.class, name = "MONGODB"),
        @JsonSubTypes.Type(value = DatabaseBackupTargetDTO.class, name = "POSTGRESQL")
})
abstract public class AbstractBackupTargetDTO {


    protected String backupTargetId;


    @NonNull
    @NotNull
    protected BackupTargetType targetType;


    @JsonIgnore
    public boolean isDatabaseType() {
        return targetType.isDatabase();
    }


    @JsonIgnore
    public boolean isFilesystemType() {
        return targetType.isFilesystem();
    }

}