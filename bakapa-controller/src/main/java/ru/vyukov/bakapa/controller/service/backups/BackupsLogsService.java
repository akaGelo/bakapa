package ru.vyukov.bakapa.controller.service.backups;

import ru.vyukov.bakapa.controller.domain.backup.BackupLogItem;

public interface BackupsLogsService {

    BackupLogItem save(BackupLogItem logItem);
}
