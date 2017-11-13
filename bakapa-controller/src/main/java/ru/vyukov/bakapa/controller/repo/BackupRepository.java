package ru.vyukov.bakapa.controller.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.Backup;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;

@Repository
public interface BackupRepository extends PagingAndSortingRepository<Backup, String>, BackupRepositoryCustom {

    Page<Backup> findAllByBackupTarget(AbstractBackupTarget backupTarget, Pageable pageable);


    Backup findOneByBackupTargetOrderByStartTimestampAsc(AbstractBackupTarget backupTarget);
}
