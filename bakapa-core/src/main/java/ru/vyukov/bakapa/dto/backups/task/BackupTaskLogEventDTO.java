package ru.vyukov.bakapa.dto.backups.task;

import lombok.NonNull;
import lombok.Value;
import org.slf4j.event.Level;

import javax.validation.constraints.NotNull;

import java.time.Instant;

import static org.slf4j.event.Level.ERROR;
import static org.slf4j.event.Level.INFO;
import static org.slf4j.event.Level.WARN;

/**
 * @author Oleg Vyukov
 */
@Value
public class BackupTaskLogEventDTO {

    public static final String DESTINATION = "/backup/log";

    @NotNull
    @NonNull
    public String backupId;

    @NotNull
    @NonNull
    private Level level;

    @NotNull
    @NonNull
    private String value;

    @NotNull
    @NonNull
    private Instant timestamp = Instant.now();


    public static BackupTaskLogEventDTO info(BackupTaskDTO backupTask, String message) {
        return new BackupTaskLogEventDTO(backupTask.getBackupId(), INFO, message);
    }

    public static BackupTaskLogEventDTO warn(BackupTaskDTO backupTask, String message) {
        return new BackupTaskLogEventDTO(backupTask.getBackupId(), WARN, message);
    }

    public static BackupTaskLogEventDTO error(BackupTaskDTO backupTask, String message) {
        return new BackupTaskLogEventDTO(backupTask.getBackupId(), ERROR, message);
    }
}
