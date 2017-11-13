package ru.vyukov.bakapa.controller.service.backups;

import ru.vyukov.bakapa.controller.domain.backup.Backup;
import ru.vyukov.bakapa.controller.domain.backup.BackupLogItem;

public class CreateBackupException extends Exception {
    public CreateBackupException(Backup newBackup, BackupLogItem logItem) {
        super("Create backup problem: [" + logItem.getMessage() + "]");
    }
}
