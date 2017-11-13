package ru.vyukov.bakapa.controller.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import ru.vyukov.bakapa.controller.domain.backup.Backup;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;

import java.util.List;
import java.util.stream.Collectors;

public class BackupRepositoryImpl implements BackupRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public Page<Backup> findAllByBackupTarget(List<AbstractBackupTarget> backupsTargets, Pageable pageable) {
        List<String> ids = backupsTargets.stream().map(AbstractBackupTarget::getBackupTargetId).collect(Collectors.toList());

        CriteriaDefinition criteria = Criteria.where("backupTarget").in(ids);
        Query query = Query.query(criteria).with(pageable);
        List<Backup> backups = mongoOperations.find(query, Backup.class);

        return PageableExecutionUtils.getPage(
                backups, pageable, () -> mongoOperations.count(query, Backup.class)
        );
    }
}
