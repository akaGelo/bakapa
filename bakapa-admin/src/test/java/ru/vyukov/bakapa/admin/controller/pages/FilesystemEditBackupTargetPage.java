package ru.vyukov.bakapa.admin.controller.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FilesystemEditBackupTargetPage extends MainLayout {
    public SelenideElement path() {
        return $("#path");
    }

    public SelenideElement saveButton() {
        return $("#saveButton");
    }
}
