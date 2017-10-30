package org.bakapa.dto.backups;

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
    public DatabaseBackupTargetDTO(String backupTargetId, BackupTargetType targetType, String host, String username, Integer port, String password, @Singular List<String> excludeTables) {
        super(backupTargetId, targetType);
        this.host = host;
        this.username = username;
        this.port = port;
        this.password = password;
        this.excludeTables = excludeTables;
    }


    private DatabaseBackupTargetDTO(String backupTargetId, BackupTargetType targetType, String host, String username, Integer port, String password) {
        this(backupTargetId, targetType, host, username, port, password, Collections.emptyList());
    }

    public static DatabaseBackupTargetDTO localhostPostgresql() {
        return new DatabaseBackupTargetDTO(null, POSTGRESQL, "localhost", "bakapa", 5432, "qwerty");
    }

    public static DatabaseBackupTargetDTO localhostMysql() {
        return new DatabaseBackupTargetDTO(null, MYSQL, "localhost", "bakapa", 3600, "qwerty");
    }

    public static DatabaseBackupTargetDTO localhostMongoDb() {
        return new DatabaseBackupTargetDTO(null, MONGODB, "localhost", "bakapa", 27018, "qwerty");
    }
}