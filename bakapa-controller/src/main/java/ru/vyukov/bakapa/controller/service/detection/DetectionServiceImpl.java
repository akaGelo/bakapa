package ru.vyukov.bakapa.controller.service.detection;

import static lombok.AccessLevel.PROTECTED;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import ru.vyukov.bakapa.controller.domain.DetectionConfig;
import ru.vyukov.bakapa.controller.repo.DetectionConfigRepository;

/**
 * Manages the detection configuration and processes the detected databases
 * 
 * @author gelo
 *
 */
@Service
public class DetectionServiceImpl implements DetectionService {

	@Autowired
	@Setter(value = PROTECTED)
	private DetectionConfigRepository detectionConfigRepository;

	@PostConstruct
	synchronized public void init() {
		if (null == getDetectionConfig()) {
			DetectionConfig config = DetectionConfig.defaultConfig();
			detectionConfigRepository.save(config);
		}
	}

	@Override
	public DetectionConfig getDetectionConfig() {
		return detectionConfigRepository.findOne(DetectionConfig.INSTANCE_ID);
	}

	@Override
	public void updateDetectionConfig(DetectionConfig config) {
		detectionConfigRepository.save(config);
	}

}