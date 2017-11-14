package ru.vyukov.bakapa.admin.controller.backups;

import org.junit.Ignore;
import org.junit.Test;
import ru.vyukov.bakapa.admin.controller.SuperUITest;
import ru.vyukov.bakapa.admin.controller.pages.BackupsPage;
import ru.vyukov.bakapa.admin.controller.pages.StorageConfigPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.fail;


public class BackupsControllerIntegrationTest extends SuperUITest {

    @Test
    public void allBackups() throws Exception {
        BackupsPage storageConfigPage = open("/backups/", BackupsPage.class);
        storageConfigPage.titleShouldHave("Backups ");
        storageConfigPage.backupsTableShouldNotEmpty();

    }


}