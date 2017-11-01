package org.bakapa.dto.backups;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.bakapa.domain.BackupTargetType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

import static org.bakapa.domain.BackupTargetType.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DatabaseBackupTargetDTO extends AbstractBackupTargetDTO {

    @NonNull
    @NotNull
    @NotEmpty
    private String host;

    @NonNull
    @NotNull
    @NotEmpty
    private String database;

    @NonNull
    @NotNull
    @NotEmpty
    private String username;

    @NonNull
    @NotNull
    @Min(0)
    private Integer port;

    @NonNull
    @NotNull
    @NotEmpty
    private String password;

    @NonNull
    @NotNull
    @Singular
    private List<String> excludeTables;


    @Builder
    public DatabaseBackupTargetDTO(String backupTargetId, String database, BackupTargetType targetType, String host, String username, Integer port, String password, @Singular List<String> excludeTables) {
        super(backupTargetId, targetType);
        this.host = host;
        this.database = database;
        this.username = username;
        this.port = port;
        this.password = password;
        this.excludeTables = excludeTables;
    }


    private DatabaseBackupTargetDTO(BackupTargetType targetType, String username, Integer port) {
        this(null, "example-name", targetType, "localhost", username, port, "qwerty", Collections.emptyList());
    }

    public static DatabaseBackupTargetDTO localhostPostgresql() {
        return new DatabaseBackupTargetDTO(POSTGRESQL, "bakapa", 5432);
    }

    public static DatabaseBackupTargetDTO localhostMysql() {
        return new DatabaseBackupTargetDTO(MYSQL, "bakapa", 3600);
    }

    public static DatabaseBackupTargetDTO localhostMongoDb() {
        return new DatabaseBackupTargetDTO(MONGODB, "bakapa", 27018);
    }
}