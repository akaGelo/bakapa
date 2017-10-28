package ru.vyukov.bakapa.controller.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vyukov.bakapa.controller.domain.config.BackupsStorage;
import ru.vyukov.bakapa.controller.domain.config.DetectionConfig;
import ru.vyukov.bakapa.controller.domain.config.RuntimeConfig;

@Repository
public interface ConfigsRepository extends CrudRepository<RuntimeConfig, String> {


    /**
     * BackupStorage ignored query parser
     *
     * @param instanceId
     * @return
     */
    BackupsStorage findOneBackupStorageById(String instanceId);

    /**
     * DetectionConfig ignored query parser
     *
     * @param instanceId
     * @return
     */
    DetectionConfig findOneDetectionConfigById(String instanceId);
}
