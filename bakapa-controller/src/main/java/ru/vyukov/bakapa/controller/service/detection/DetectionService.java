package ru.vyukov.bakapa.controller.service.detection;

import ru.vyukov.bakapa.controller.domain.config.DetectionConfig;

public interface DetectionService {

	DetectionConfig getDetectionConfig();

	void updateDetectionConfig(DetectionConfig config);

}
