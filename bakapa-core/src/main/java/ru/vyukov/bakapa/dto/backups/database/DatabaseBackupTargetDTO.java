package ru.vyukov.bakapa.dto.backups.database;

import lombok.*;
import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.dto.backups.AbstractBackupTargetDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static ru.vyukov.bakapa.domain.BackupTargetType.*;
import static ru.vyukov.bakapa.dto.backups.database.DatabaseBackupOptionsDTO.backupOptions;

@Data
@NoArgsConstructor
public class DatabaseBackupTargetDTO extends AbstractBackupTargetDTO {


    @Valid
    @NotNull
    private DatabaseLocationDTO location;

    @Valid
    @NotNull
    private DatabaseUserCredentialsDTO userCredentials;

    @Valid
    @NotNull
    private DatabaseBackupOptionsDTO options;


    @Builder(toBuilder = true)
    private DatabaseBackupTargetDTO(String backupTargetId, BackupTargetType targetType,String trigger, DatabaseLocationDTO location, DatabaseUserCredentialsDTO userCredentials, DatabaseBackupOptionsDTO options) {
        super(backupTargetId, targetType,trigger);
        this.location = location;
        this.userCredentials = userCredentials;
        this.options = options;
    }


    private DatabaseBackupTargetDTO(BackupTargetType targetType, Integer port) {
        this(null, targetType,EVERY_DAY_CRON_TRIGGER,
                DatabaseLocationDTO.databaseLocation().database("example-db").host("localhost").port(port).build(),
                DatabaseUserCredentialsDTO.userCredentials().username("bakapa").password("qwerty").build(),
                backupOptions().excludeTable("exclude_table_example").build()
        );
    }

    public static DatabaseBackupTargetDTO localhostPostgresql() {
        return new DatabaseBackupTargetDTO(POSTGRESQL, 5432);
    }

    public static DatabaseBackupTargetDTO localhostMysql() {
        return new DatabaseBackupTargetDTO(MYSQL, 3600);
    }

    public static DatabaseBackupTargetDTO localhostMongoDb() {
        return new DatabaseBackupTargetDTO(MONGODB, 27018);
    }
}