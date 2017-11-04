package ru.vyukov.bakapa.admin.controller.pages;

import ru.vyukov.bakapa.dto.config.StorageConfigDTO;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class StorageConfigPage extends MainLayout {

    public StorageConfigPage sendForm(StorageConfigDTO storageConfigDTO) {
        $("#endpoint").setValue(storageConfigDTO.getEndpoint());
        $("#accessKey").setValue(storageConfigDTO.getAccessKey());
        $("#secretKey").setValue(storageConfigDTO.getSecretKey());
        $("#bucket").setValue(storageConfigDTO.getBucket());
        $("#saveButton").click();


        return page(StorageConfigPage.class);
    }
}
