package ru.vyukov.bakapa.admin.controller.config;


import org.bakapa.dto.config.StorageConfigDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vyukov.bakapa.admin.controller.SuperUIController;
import ru.vyukov.bakapa.admin.service.agents.ConfigApiClient;

@Controller
public class BackupStorageConfigController extends SuperUIController {

    @Autowired
    private ConfigApiClient configApiClient;

    @GetMapping("/storage/config/")
    public String storage(Model model) {
        StorageConfigDTO storageConfig = configApiClient.getStorageConfig();
        model.addAttribute("storageConfig", storageConfig);
        return "storage";
    }


    @PostMapping("/storage/config/")
    public String updateStorageConfig(@ModelAttribute("storageConfig") @Validated StorageConfigDTO storageConfig, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            dangerMessage(model, bindingResult);
        } else {
            configApiClient.saveStorageConfig(storageConfig);
            successMessage(model, "Successfully saved");
        }
        return "storage";
    }
}
