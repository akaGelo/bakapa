package ru.vyukov.bakapa.agent.service.backup;

public interface BackupToolsProvider {

	BackupTools getTools(BackupConfig backupConfig);

}
