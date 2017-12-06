package ru.vyukov.bakapa.agent.service.backup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vyukov.bakapa.dto.backups.task.BackupTaskDTO;
import ru.vyukov.bakapa.dump.DumpUtilWrapper;

/**
 * @author Oleg Vyukov
 */
@Component
class BackupScriptFactoryImpl implements BackupScriptFactory {


    private DumpUtilFactory dumpUtilFactory;

    @Autowired
    public BackupScriptFactoryImpl(DumpUtilFactory dumpUtilFactory) {
        this.dumpUtilFactory = dumpUtilFactory;
    }


    @Override
    public BackupScript create(BackupTaskDTO backupTaskDTO, BackupServiceConfig backupServiceConfig, Logger logger) {

        DumpUtilWrapper dumpUtilWrapper = dumpUtilFactory.create(backupTaskDTO);

        return BackupScriptImpl.builder()
                .dumpUtilWrapper(dumpUtilWrapper)
                .backupServiceConfig(backupServiceConfig)
                .logger(logger)
                .build();
    }
}
