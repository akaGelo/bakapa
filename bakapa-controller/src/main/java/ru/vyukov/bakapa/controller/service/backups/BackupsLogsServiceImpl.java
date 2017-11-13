package ru.vyukov.bakapa.controller.service.backups;

import org.springframework.stereotype.Service;
import ru.vyukov.bakapa.controller.domain.backup.BackupLogItem;

@Service
public class BackupsLogsServiceImpl implements BackupsLogsService {

    @Override
    public BackupLogItem save(BackupLogItem logItem) {
        return logItem;
    }
}
