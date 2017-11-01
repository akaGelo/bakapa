package ru.vyukov.bakapa.admin.controller.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NewAgentPage {

    public SelenideElement agentId() {
        return $("#newAgentId");
    }

    public SelenideElement agentPassword() {
        return $("#newAgentPassword");
    }
}
