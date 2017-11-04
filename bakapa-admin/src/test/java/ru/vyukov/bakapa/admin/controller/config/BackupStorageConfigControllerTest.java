package ru.vyukov.bakapa.admin.controller.config;

import ru.vyukov.bakapa.dto.config.StorageConfigDTO;
import org.junit.Test;
import ru.vyukov.bakapa.admin.controller.SuperUITest;
import ru.vyukov.bakapa.admin.controller.pages.StorageConfigPage;

import static com.codeborne.selenide.Selenide.open;

public class BackupStorageConfigControllerTest extends SuperUITest {
    @Test
    public void storage() throws Exception {
        StorageConfigPage storageConfigPage = open("/storage/config/", StorageConfigPage.class);
        storageConfigPage.titleShouldHave("Backups storage config");
    }

    @Test
    public void updateStorageConfig() throws Exception {
        StorageConfigPage storageConfigPage = open("/storage/config/", StorageConfigPage.class);

        StorageConfigDTO storageConfigDTO = new StorageConfigDTO("http://minio.io", "test", "access", "secret");
        storageConfigPage = storageConfigPage.sendForm(storageConfigDTO);
        storageConfigPage.successAlertShouldHave("Successfully saved");
    }


    @Test
    public void updateStorageConfigValidatorCheck() throws Exception {
        StorageConfigPage storageConfigPage = open("/storage/config/", StorageConfigPage.class);

        StorageConfigDTO storageConfigDTO = new StorageConfigDTO("minio.io", "test", "access", "");

        storageConfigPage = storageConfigPage.sendForm(storageConfigDTO);
        storageConfigPage.dangerAlertShouldHave("endpoint");
        storageConfigPage.dangerAlertShouldHave("secret");

    }


}