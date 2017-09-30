package ru.vyukov.bakapa.controller.controller.priv;

import org.bakapa.dto.DetectionConfigDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.vyukov.bakapa.controller.domain.DetectionConfig;
import ru.vyukov.bakapa.controller.service.detection.DetectionService;

@RestController
@ExposesResourceFor(DetectionConfigDTO.class)
@RequestMapping("/private/detection/config")
public class DetectionApiController extends SuperPrivateController {

	@Autowired
	private DetectionService detectionService;

	@Autowired
	private EntityLinks entityLinks;

	@GetMapping
	public Resource<DetectionConfigDTO> getDetectionConfig() {
		DetectionConfigDTO dto = convertDTO(detectionService.getDetectionConfig(), DetectionConfigDTO.class);
		Resource<DetectionConfigDTO> resource = new Resource<>(dto);
		resource.add(this.entityLinks.linkToSingleResource(DetectionConfigDTO.class, null));
		return resource;
	}

	@PostMapping
	public void updateDetectionConfig(DetectionConfigDTO detectionConfigDTO) {
		DetectionConfig config = convertDTO(detectionConfigDTO, DetectionConfig.class);
		detectionService.updateDetectionConfig(config);
	}
}
