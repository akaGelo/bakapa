package ru.vyukov.bakapa.admin.controller.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BackupsPage extends MainLayout {

    public void backupsTableShouldNotEmpty() {
        $("#backups-table").shouldHave(text("Name"));
        $("#backups-table").shouldHave(text("Status"));
        $("#backups-table").shouldHave(text("INPROGRESS"));

        $(".backupState").shouldBe(visible);
    }

    public void backupsTableShouldEmpty() {
        $("#no-backups-message").shouldBe(visible);

    }

    public SelenideElement nextPageButton() {
        return $("#pagination-next");
    }

    public SelenideElement currentPageIndicator() {
        return $("#pagination-current-page");
    }

    public SelenideElement previousPageButton() {
        return $("#pagination-previous");
    }


}
