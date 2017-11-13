package ru.vyukov.bakapa.controller.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;

import java.util.List;

@Repository
public interface BackupsTargetsRepository extends CrudRepository<AbstractBackupTarget, String> {


    List<AbstractBackupTarget> findAllByAgent(Agent agent);

    Page<AbstractBackupTarget> findAllByOrderByBackupTargetIdAsc(Pageable pageable);

    /**
     * Use as default find all
     *
     * @return
     */
    List<AbstractBackupTarget> findAllByOrderByBackupTargetIdAsc();
}