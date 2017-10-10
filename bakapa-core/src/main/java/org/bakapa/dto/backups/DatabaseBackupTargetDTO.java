package org.bakapa.dto.backups;

import lombok.Builder;
import lombok.NonNull;
import org.bakapa.domain.BackupTargetType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DatabaseBackupTargetDTO extends  AbstractBackupTargetDTO {

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


    @Builder
    public DatabaseBackupTargetDTO(BackupTargetType targetType, String host, String username, Integer port, String password) {
        super(targetType);
        this.host = host;
        this.username = username;
        this.port = port;
        this.password = password;
    }

}