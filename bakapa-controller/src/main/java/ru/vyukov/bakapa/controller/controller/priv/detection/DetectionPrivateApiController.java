package ru.vyukov.bakapa.controller.controller.priv.detection;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vyukov.bakapa.controller.controller.priv.SuperPrivateController;
import ru.vyukov.bakapa.controller.domain.View.Full;
import ru.vyukov.bakapa.controller.domain.config.DetectionConfig;
import ru.vyukov.bakapa.controller.service.detection.DetectionService;

@RestController
@RequestMapping("/private/config/config")
public class DetectionPrivateApiController extends SuperPrivateController {

    @Autowired
    private DetectionService detectionService;


    @GetMapping
    @JsonView(Full.class)
    public DetectionConfig getDetectionConfig() {
        DetectionConfig detectionConfig = detectionService.getDetectionConfig();
        return detectionConfig;
    }

    @PostMapping
    public void updateDetectionConfig(@RequestBody DetectionConfig detectionConfig) {
        detectionService.updateDetectionConfig(detectionConfig);
    }
}