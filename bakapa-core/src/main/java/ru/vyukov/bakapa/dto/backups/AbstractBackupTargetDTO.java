package ru.vyukov.bakapa.dto.backups;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.dto.backups.database.DatabaseBackupTargetDTO;
import ru.vyukov.bakapa.validators.CronExpression;

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

    public static final String EVERY_DAY_CRON_TRIGGER = "0 0 0 * * *";

    protected String backupTargetId;


    @NonNull
    @NotNull
    protected BackupTargetType targetType;

    @NonNull
    @NotNull
    @CronExpression
    protected String trigger;



    @JsonIgnore
    public boolean isDatabaseType() {
        return targetType.isDatabase();
    }


    @JsonIgnore
    public boolean isFilesystemType() {
        return targetType.isFilesystem();
    }

}