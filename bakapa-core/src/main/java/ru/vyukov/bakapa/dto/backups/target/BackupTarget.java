package ru.vyukov.bakapa.dto.backups.target;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.vyukov.bakapa.domain.BackupTargetType;

public interface BackupTarget {

    public static final String EVERY_DAY_CRON_TRIGGER = "0 0 0 * * *";

    BackupTargetType getTargetType();

    String getBackupTargetId();

    void setBackupTargetId(String backupTargetId);

    @JsonIgnore
    public abstract String getNameReadable();
}
