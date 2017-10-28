package ru.vyukov.bakapa.controller.service.backups;

public class BackupTargetNotFoundException extends Exception {
    public BackupTargetNotFoundException(String backupTargetId) {
        super("Backup target [" + backupTargetId + "] not found");
    }
}
