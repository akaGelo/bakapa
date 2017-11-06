package ru.vyukov.bakapa.admin.controller.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

abstract public class EditTargetPage extends MainLayout {

    public void setCronExpression(String value) {
        cronExpression().setValue(value);
        cronExpression().sendKeys(Keys.SPACE);
        cronExpression().sendKeys(Keys.BACK_SPACE);
    }

    public SelenideElement cronExpression() {
        return $("#trigger");
    }

    public SelenideElement cronExpressionValidateResult() {
        return $("#trigger-validate");
    }
}
