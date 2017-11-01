package ru.vyukov.bakapa.admin.controller.agents;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import ru.vyukov.bakapa.admin.controller.pages.AgentsPage;
import ru.vyukov.bakapa.admin.controller.pages.MainLayout;
import ru.vyukov.bakapa.admin.controller.SuperUITest;
import ru.vyukov.bakapa.admin.controller.pages.NewAgentPage;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class AgentsControllerTest extends SuperUITest {


    @Test
    public void agents() throws Exception {
        AgentsPage agentsPage = open("/agents/", AgentsPage.class);
        agentsPage.titleShouldHave("Agents");
    }

    @Test
    public void createAgent() throws Exception {
        AgentsPage agentsPage = open("/agents/", AgentsPage.class);
        NewAgentPage newAgentPage = agentsPage.createNewAgent("superAgent");

        newAgentPage.agentId().shouldBe(text("superAgent"));
        newAgentPage.agentPassword().shouldBe(not(Condition.empty));
    }


    @Test
    public void createAgentInvalidId() throws Exception {
        AgentsPage agentsPage = open("/agents/", AgentsPage.class);
        agentsPage.newAgentForm().submit();
        agentsPage.dangerAlertShouldHave("agentId");
    }


}