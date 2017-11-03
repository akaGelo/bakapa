package org.bakapa.dto.backups.database;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;
import org.bakapa.domain.BackupTargetType;
import org.bakapa.dto.backups.AbstractBackupTargetDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;

import static org.bakapa.domain.BackupTargetType.*;
import static org.bakapa.dto.backups.database.DatabaseBackupOptionsDTO.backupOptions;
import static org.bakapa.dto.backups.database.DatabaseLocationDTO.databaseLocation;
import static org.bakapa.dto.backups.database.DatabaseUserCredentialsDTO.userCredentials;

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
    private DatabaseBackupTargetDTO(String backupTargetId, BackupTargetType targetType, DatabaseLocationDTO location, DatabaseUserCredentialsDTO userCredentials, DatabaseBackupOptionsDTO options) {
        super(backupTargetId, targetType);
        this.location = location;
        this.userCredentials = userCredentials;
        this.options = options;
    }


    private DatabaseBackupTargetDTO(BackupTargetType targetType, Integer port) {
        this(null, targetType,
                databaseLocation().database("example-db").host("localhost").port(port).build(),
                userCredentials().username("bakapa").password("qwerty").build(),
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