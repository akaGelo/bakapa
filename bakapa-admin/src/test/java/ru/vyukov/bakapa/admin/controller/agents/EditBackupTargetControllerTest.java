package ru.vyukov.bakapa.admin.controller.agents;

import org.junit.Test;
import ru.vyukov.bakapa.admin.controller.SuperUITest;
import ru.vyukov.bakapa.admin.controller.pages.AgentsPage;
import ru.vyukov.bakapa.admin.controller.pages.DatabaseEditBackupTargetPage;
import ru.vyukov.bakapa.admin.controller.pages.EditTargetPage;
import ru.vyukov.bakapa.admin.controller.pages.FilesystemEditBackupTargetPage;
import sun.management.resources.agent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class EditBackupTargetControllerTest extends SuperUITest {


    @Test
    public void editDatabase() throws Exception {
        AgentsPage agentsPage = open("/agents/", AgentsPage.class);
        DatabaseEditBackupTargetPage editPage = agentsPage.firstAgentDropDownItemClick("Database", DatabaseEditBackupTargetPage.class);


        testTriggerPreview(editPage);

        editPage.password("keys");
        editPage.excludedTables().setValue("table1,table1").submit();


        editPage.successAlertShouldHave("Successfully saved");
    }

    private void testTriggerPreview(EditTargetPage editPage) {
        editPage.setCronExpression("0 0 0 * *");
        editPage.cronExpressionValidateResult().shouldBe(text("Wrong expression"));

        editPage.setCronExpression("0 0 0 * * *");
        editPage.cronExpressionValidateResult().shouldHave(text("Next execution"));
    }

    @Test
    public void editFilesystem() throws Exception {
        AgentsPage agentsPage = open("/agents/", AgentsPage.class);
        FilesystemEditBackupTargetPage editPage = agentsPage.firstAgentDropDownItemClick("Filesystem", FilesystemEditBackupTargetPage.class);

        editPage.path().setValue("/etc/test/");

        testTriggerPreview(editPage);

        editPage.saveButton().click();

        editPage.successAlertShouldHave("Successfully saved");
    }


}