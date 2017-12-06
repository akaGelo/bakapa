package ru.vyukov.bakapa.controller.domain.backup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.domain.config.StorageConfig;

import javax.validation.constraints.NotNull;

/**
 * @author Oleg Vyukov
 */
@Data
@Builder
@AllArgsConstructor
public class BackupTask {

    @NotNull
    @NonNull
    private String backupId;

    @NotNull
    @NonNull
    private AbstractBackupTarget backupTarget;

    @NotNull
    @NonNull
    private StorageConfig storageConfig;

    public static BackupTask demo() {
        return builder()
                .backupId("demoId")
                .backupTarget(AbstractBackupTarget.demo())
                .storageConfig(StorageConfig.defaultConfig())
                .build();
    }
}
