package ru.vyukov.bakapa.admin.controller;


import org.bakapa.dto.backups.BackupsStorageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vyukov.bakapa.admin.service.agents.StorageApiClient;

@Controller
public class BackupStorageConfigController extends SuperUIController {

    @Autowired
    private StorageApiClient storageApiClient;

    @GetMapping("/storage/config/")
    public String storage(Model model) {
        BackupsStorageDTO storageConfig = storageApiClient.getStorageConfig();
        model.addAttribute("storageConfig", storageConfig);
        return "storage";
    }


    @PostMapping("/storage/config/")
    public String updateStorageConfig(@ModelAttribute("storageConfig") @Validated BackupsStorageDTO backupsStorageDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            dangerMessage(model, bindingResult);
        }
        return "storage";
    }
}
