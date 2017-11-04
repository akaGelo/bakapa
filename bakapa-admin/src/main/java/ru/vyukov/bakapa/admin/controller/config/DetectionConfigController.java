package ru.vyukov.bakapa.admin.controller.config;


import ru.vyukov.bakapa.dto.config.DetectionConfigDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vyukov.bakapa.admin.controller.SuperUIController;
import ru.vyukov.bakapa.admin.service.agents.ConfigApiClient;

@Controller
public class DetectionConfigController extends SuperUIController {

    @Autowired
    private ConfigApiClient configApiClient;

    @GetMapping("/detection/config/")
    public String detection(Model model) {
        DetectionConfigDTO detectionConfig = configApiClient.getDetectionConfig();
        model.addAttribute("detectionConfig", detectionConfig);
        return "detection";
    }


    @PostMapping("/detection/config/")
    public String updateDetectionConfig(@ModelAttribute("detectionConfig") @Validated DetectionConfigDTO detectionConfig, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            dangerMessage(model, bindingResult);
        } else {
            configApiClient.saveDetectionConfig(detectionConfig);
            successMessage(model, "Successfully saved");

        }
        return "detection";
    }
}
