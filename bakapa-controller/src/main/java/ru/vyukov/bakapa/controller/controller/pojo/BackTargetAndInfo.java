package ru.vyukov.bakapa.controller.controller.pojo;

import lombok.Builder;
import org.bakapa.dto.backups.AbstractBackTargetAndInfoDTO;
import org.bakapa.dto.backups.BackupTargetExecutionInfo;
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;

public class BackTargetAndInfo extends AbstractBackTargetAndInfoDTO<AbstractBackupTarget> {

    @Builder
    public BackTargetAndInfo(AbstractBackupTarget backupTarget, BackupTargetExecutionInfo executionInfo) {
        super(backupTarget, executionInfo);
    }
}
