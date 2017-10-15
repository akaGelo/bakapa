package ru.vyukov.bakapa.controller.controller.priv;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vyukov.bakapa.controller.domain.DetectionConfig;
import ru.vyukov.bakapa.controller.domain.View;
import ru.vyukov.bakapa.controller.domain.View.Full;
import ru.vyukov.bakapa.controller.service.detection.DetectionService;

@RestController
@RequestMapping("/private/detection/config")
public class DetectionPrivateApiController extends SuperPrivateController {

    @Autowired
    private DetectionService detectionService;


    @GetMapping
    @JsonView(Full.class)
    public DetectionConfig getDetectionConfig() {
        return detectionService.getDetectionConfig();
    }

    @PostMapping
    public void updateDetectionConfig(DetectionConfig detectionConfig) {
        detectionService.updateDetectionConfig(detectionConfig);
    }
}
