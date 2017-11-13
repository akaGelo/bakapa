package ru.vyukov.bakapa.controller.domain.backup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.vyukov.bakapa.controller.domain.View;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;
import ru.vyukov.bakapa.domain.BackupState;

import javax.validation.constraints.NotNull;
import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;
import static ru.vyukov.bakapa.domain.BackupState.ERROR;
import static ru.vyukov.bakapa.domain.BackupState.INPROGRESS;

/**
 * Base artifact - files and other output shit
 */
@Data
@EqualsAndHashCode(of = "backupId")
@Document(collection = "backup")
@RequiredArgsConstructor(access = PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Backup {

    @Id
    @JsonView(Summary.class)
    private String backupId;

    @DBRef
    @Indexed
    @NotNull
    @NonNull
    @JsonView(Summary.class)
    private AbstractBackupTarget backupTarget;


    @Indexed
    @NotNull
    @NonNull
    @JsonView(Summary.class)
    private Instant startTimestamp;


    @JsonView(Summary.class)
    private Instant finishTimestamp;


    @Indexed
    @NotNull
    @JsonView(Summary.class)
    private BackupState state = INPROGRESS;

    @JsonView(Summary.class)
    private long size = 0L;

    static public Backup newBackup(AbstractBackupTarget backupTarget) {
        return new Backup(backupTarget, Instant.now());
    }


    public static Backup newStoppedBackup(AbstractBackupTarget backupTarget) {
        return new Backup(null, backupTarget, Instant.now(), Instant.now(), ERROR, 0L);
    }


    public static Backup demo(AbstractBackupTarget backupTarget) {
        return newBackup(backupTarget);
    }

    @JsonIgnore
    public boolean isNotFinished() {
        return state.equals(INPROGRESS);
    }
}

