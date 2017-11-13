package ru.vyukov.bakapa.controller.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vyukov.bakapa.controller.domain.backup.Backup;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;

import java.util.List;

public interface BackupRepositoryCustom {

    Page<Backup> findAllByBackupTarget(List<AbstractBackupTarget> backupsTargets, Pageable pageable);
}
