package ru.vyukov.bakapa.admin.controller.agents;

import org.junit.Test;
import ru.vyukov.bakapa.admin.controller.SuperUITest;
import ru.vyukov.bakapa.admin.controller.pages.AgentsPage;
import ru.vyukov.bakapa.admin.controller.pages.BackupsTargetsPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;

public class BackupTargetsOnAgentControllerTest extends SuperUITest{

    @Test
    public void targets() throws Exception {
        AgentsPage agentsPage = open("/agents/", AgentsPage.class);

        BackupsTargetsPage targetsPage =  agentsPage.firstAgentBackupsTargets();
        targetsPage.titleShouldHave("Backups targets on ");
    }

    //TODO test EDIT button

    //TODO text backups button

}