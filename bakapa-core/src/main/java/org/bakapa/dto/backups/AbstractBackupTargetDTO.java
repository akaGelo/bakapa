package org.bakapa.dto.backups;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import org.bakapa.domain.BackupTargetType;
import org.bakapa.dto.agent.AgentDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "targetType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DirectoryBackupTargetDTO.class, name = "FILESYSTEM"),
        //
        @JsonSubTypes.Type(value = DatabaseBackupTargetDTO.class, name = "MYSQL"),
        @JsonSubTypes.Type(value = DatabaseBackupTargetDTO.class, name = "MONGODB"),
        @JsonSubTypes.Type(value = DatabaseBackupTargetDTO.class, name = "POSTGRESQL")
})
abstract public class AbstractBackupTargetDTO {


    private String backupTargetId;


    @NonNull
    @NotNull
    private BackupTargetType targetType;


    @JsonIgnore
    public boolean isDatabase() {
        return targetType.isDatabase();
    }


    @JsonIgnore
    public boolean isFilesystem() {
        return targetType.isFilesystem();
    }

}