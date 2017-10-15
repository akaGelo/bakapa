package ru.vyukov.bakapa.controller.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vyukov.bakapa.controller.domain.BackupsStorage;
import ru.vyukov.bakapa.controller.domain.DetectionConfig;
import ru.vyukov.bakapa.controller.domain.RuntimeConfig;

@Repository
public interface ConfigsRepository extends CrudRepository<RuntimeConfig, String> {


    /**
     * BackupStorage ignored query parser
     *
     * @param instanceId
     * @return
     */
    BackupsStorage findOneBackupStorageBy(String instanceId);

    /**
     * DetectionConfig ignored query parser
     *
     * @param instanceId
     * @return
     */
    DetectionConfig findOneDetectionConfigBy(String instanceId);
}
