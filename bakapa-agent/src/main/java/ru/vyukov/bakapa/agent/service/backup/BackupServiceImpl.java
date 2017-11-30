package ru.vyukov.bakapa.agent.service.backup;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Oleg Vyukov
 */
public class BackupServiceImpl implements BackupService {


    private BackupServiceConfig backupServiceConfig;

    @Autowired
    public BackupServiceImpl(BackupServiceConfig backupServiceConfig) {
        this.backupServiceConfig = backupServiceConfig;
    }
}
