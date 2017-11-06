package ru.vyukov.bakapa.admin.controller.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DatabaseEditBackupTargetPage extends EditTargetPage {
    public SelenideElement password() {
        return $("#userCredentials\\.password");
    }

    public SelenideElement excludedTables() {
        return $("#options\\.excludeTables");
    }

    public SelenideElement saveButton() {
        return $("#saveButton");
    }


    public void password(String value) {
        password().setValue(value);
    }
}
