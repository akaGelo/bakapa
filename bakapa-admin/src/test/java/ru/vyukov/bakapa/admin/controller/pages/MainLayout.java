package ru.vyukov.bakapa.admin.controller.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainLayout {


    public void titleShouldHave(String text) {
        $(".navbar-brand").shouldHave(text(text));
    }

    public static void dangerAlertsHhouldHave(String fieldNameOrTextFragment, int count) {
        $$(".alert-danger").filter(text(fieldNameOrTextFragment)).shouldHaveSize(count);
    }

    public static void dangerAlertShouldHave(String fieldNameOrTextFragment) {
        dangerAlertsHhouldHave(fieldNameOrTextFragment, 1);
    }

    public static void successAlertShouldHave(String subText) {
        $$(".alert-success").filter(text(subText)).shouldHaveSize(1);
    }


}
