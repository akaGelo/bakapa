package ru.vyukov.bakapa.controller.service.detection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.vyukov.bakapa.controller.domain.config.DetectionConfig;
import ru.vyukov.bakapa.controller.repo.ConfigsRepository;

/**
 * Manages the contracts.controller.controller.contracts.controller.controller.priv.config configuration and processes the detected databases
 *
 * @author gelo
 */
@Service
public class DetectionServiceImpl implements DetectionService {


    private ConfigsRepository configsRepository;

    @Autowired
    public DetectionServiceImpl(ConfigsRepository configsRepository) {
        this.configsRepository = configsRepository;
    }

    @PostConstruct
    synchronized public void init() {
        if (null == getDetectionConfig()) {
            DetectionConfig config = DetectionConfig.defaultConfig();
            configsRepository.save(config);
        }
    }

    @Override
    public DetectionConfig getDetectionConfig() {
        return configsRepository.findOneDetectionConfigById(DetectionConfig.INSTANCE_ID);
    }

    @Override
    public void updateDetectionConfig(DetectionConfig config) {
        configsRepository.save(config);
    }

}