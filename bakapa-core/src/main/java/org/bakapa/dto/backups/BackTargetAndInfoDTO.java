package org.bakapa.dto.backups;

import org.bakapa.dto.BackupTaskDTO;

public class BackTargetAndInfoDTO extends AbstractBackTargetAndInfoDTO<AbstractBackupTargetDTO> {


    public BackTargetAndInfoDTO(AbstractBackupTargetDTO backupTarget, BackupTargetExecutionInfo executionInfo) {
        super(backupTarget, executionInfo);
    }
}
