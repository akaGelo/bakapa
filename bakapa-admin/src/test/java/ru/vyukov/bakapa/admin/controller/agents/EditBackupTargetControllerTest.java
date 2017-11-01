package ru.vyukov.bakapa.admin.controller.agents;

import org.junit.Test;
import ru.vyukov.bakapa.admin.controller.SuperUITest;
import ru.vyukov.bakapa.admin.controller.pages.AgentsPage;
import ru.vyukov.bakapa.admin.controller.pages.DatabaseEditBackupTargetPage;
import sun.management.resources.agent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.vyukov.bakapa.admin.controller.pages.MainLayout.successAlertShouldHave;

public class EditBackupTargetControllerTest extends SuperUITest{


    @Test
    public void editDatabase() throws Exception {
        AgentsPage agentsPage = open("/agents/", AgentsPage.class);
        DatabaseEditBackupTargetPage editPage = agentsPage.firstAgentDropDownItemClick("Database");

        editPage.password().sendKeys("keys");
        editPage.excludedTables().setValue("table1,table1").submit();

        successAlertShouldHave("Successfully saved");
    }

    @Test
    public void editFilesystem() throws Exception {
        AgentsPage agentsPage = open("/agents/", AgentsPage.class);
        DatabaseEditBackupTargetPage editPage = agentsPage.firstAgentDropDownItemClick("Filesystem");

        $("#path").setValue("/etc/test/");

        editPage.saveButton().click();

        successAlertShouldHave("Successfully saved");
    }


}