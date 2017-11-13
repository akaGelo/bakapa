package ru.vyukov.bakapa.controller.domain.backup;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Value
@AllArgsConstructor
@EqualsAndHashCode(of = "logItemId")
@Document(collection = "backupLog")
public class BackupLogItem {

    @Id
    private String logItemId;

    @DBRef
    @Indexed
    @NotNull
    @NonNull
    private Backup backup;

    @NotNull
    @NonNull
    private String message;


    public static BackupLogItem startError(Backup backup, String message) {
        return new BackupLogItem(null, backup, message);
    }
}
