package ru.vyukov.bakapa.dto.backups.task;

import lombok.NonNull;
import lombok.Value;
import org.slf4j.event.Level;
import ru.vyukov.bakapa.domain.BackupState;

import javax.validation.constraints.NotNull;
import java.time.Instant;

import static ru.vyukov.bakapa.domain.BackupState.ERROR;
import static ru.vyukov.bakapa.domain.BackupState.SUCCESS;

/**
 * @author Oleg Vyukov
 */
@Value
public class BackupTaskFinishEventDTO {

    public static final String DESTINATION = "/backup/finish";

    @NotNull
    @NonNull
    public String backupId;

    @NotNull
    @NonNull
    private BackupState backupState;

    @NotNull
    @NonNull
    private Instant timestamp = Instant.now();

    public static BackupTaskFinishEventDTO success(BackupTaskDTO backupTask) {
        return new BackupTaskFinishEventDTO(backupTask.getBackupId(), SUCCESS);
    }

    public static BackupTaskFinishEventDTO fail(BackupTaskDTO backupTask) {
        return new BackupTaskFinishEventDTO(backupTask.getBackupId(), ERROR);
    }
}
