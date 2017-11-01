package ru.vyukov.bakapa.admin.controller.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DatabaseEditBackupTargetPage extends MainLayout {
    public SelenideElement password() {
        return $("#password");
    }

    public SelenideElement excludedTables() {
        return $("#excludeTables");
    }

    public SelenideElement saveButton() {
        return $("#saveButton");
    }
}
