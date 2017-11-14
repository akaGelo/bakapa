package ru.vyukov.bakapa.admin.controller;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

public class DashboardControllerIntegrationTest extends SuperUITest {


    @Test
    public void agents() throws Exception {
        open("/");
        $(".navbar-brand").shouldHave(Condition.text("Dashboard"));
        //TODO test content
    }


}
