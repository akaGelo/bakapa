package ru.vyukov.bakapa.controller.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.bakapa.domain.BackupTargetType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Target database
 */
@Getter
public class DatabaseBackupTarget extends AbstractBackupTarget {

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
    public DatabaseBackupTarget(BackupTargetType targetType, String host, String username, Integer port, String password) {
        super(targetType);
        this.host = host;
        this.username = username;
        this.port = port;
        this.password = password;
    }


}