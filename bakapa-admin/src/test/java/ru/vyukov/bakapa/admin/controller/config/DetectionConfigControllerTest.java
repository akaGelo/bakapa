package ru.vyukov.bakapa.admin.controller.config;

import org.bakapa.dto.config.DetectionConfigDTO;
import org.junit.Test;
import ru.vyukov.bakapa.admin.controller.SuperUITest;
import ru.vyukov.bakapa.admin.controller.pages.DetectionConfigPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;

public class DetectionConfigControllerTest extends SuperUITest {
    @Test
    public void detection() throws Exception {
        DetectionConfigPage detectionConfigPage = open("/detection/config/", DetectionConfigPage.class);
        detectionConfigPage.titleShouldHave("Detection config\n");
    }

    @Test
    public void updateDetectionConfig() throws Exception {
        DetectionConfigPage detectionConfigPage = open("/detection/config/", DetectionConfigPage.class);

        DetectionConfigDTO config = DetectionConfigDTO.builder().mongoPort(27017).mysqlPort(3306).postgresqlPort(5432).build();
        detectionConfigPage =  detectionConfigPage.sendForm(config);
        detectionConfigPage.successAlertShouldHave("Successfully saved");
    }

    @Test
    public void updateDetectionConfigValidator() throws Exception {
        DetectionConfigPage detectionConfigPage = open("/detection/config/", DetectionConfigPage.class);

        DetectionConfigDTO config = DetectionConfigDTO.builder().mysqlPort(3306).postgresqlPort(5432).mongoPort(-100).build();
        detectionConfigPage =  detectionConfigPage.sendForm(config);
        detectionConfigPage.dangerAlertShouldHave("mongoPorts");
    }

}