package ru.vyukov.bakapa.controller.domain;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.domain.config.StorageConfig;

import javax.validation.constraints.NotNull;

/**
 * Task on backup
 */
@Data
@Document(collection = "backupTask")
public class BackupTask {

    @Id
    private String taskId;

    @DBRef
    @NotNull
    @NonNull
    private Agent agent;


    @NotNull
    @NonNull
    private String cronExtension;


    @NotNull
    @NonNull
    private AbstractBackupTarget target;



    @NotNull
    @NonNull
    @DBRef
    private StorageConfig storageConfig;

}
