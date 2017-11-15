package ru.vyukov.bakapa.admin.controller.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

abstract public class EditTargetPage extends MainLayout {

    public void setCronExpression(String value) {
        cronExpression().clear();
        cronExpression().sendKeys(value);
    }

    public SelenideElement cronExpression() {
        return $("#trigger");
    }

    public SelenideElement cronExpressionValidateResult() {
        return $("#trigger-validate");
    }
}
