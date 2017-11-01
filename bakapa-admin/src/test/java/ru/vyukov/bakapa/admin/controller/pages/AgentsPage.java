package ru.vyukov.bakapa.admin.controller.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.concurrent.CompletionService;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class AgentsPage extends MainLayout {


    public NewAgentPage createNewAgent(String superAgent) {
        $("#agentId").setValue("superAgent").pressEnter();
        return page(NewAgentPage.class);
    }

    public SelenideElement newAgentForm() {
        return $("#newAgent");
    }



    public DatabaseEditBackupTargetPage firstAgentDropDownItemClick(String name) {
        $(".new-backup-btn").click();
        $(".new-backup-btn").parent().findAll("a").filter(text(name)).first().click();
        return page(DatabaseEditBackupTargetPage.class);


    }

    public BackupsTargetsPage firstAgentBackupsTargets() {
        $(".targets-count") .click();
        return  page(BackupsTargetsPage.class);
    }
}
