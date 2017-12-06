package ru.vyukov.bakapa.dump;

import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupOptionsDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseLocationDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.DatabaseUserCredentialsDTO;

/**
 * @author Oleg Vyukov
 */
public abstract class AbstractDatabaseDumpWrapperUtil {

    protected final DatabaseUserCredentialsDTO userCredentials;
    protected final DatabaseLocationDTO location;
    protected final DatabaseBackupOptionsDTO options;

    public AbstractDatabaseDumpWrapperUtil(DatabaseBackupTargetDTO databaseBackupTarget) {
        options = databaseBackupTarget.getOptions();
        location = databaseBackupTarget.getLocation();
        userCredentials = databaseBackupTarget.getUserCredentials();
    }
}
