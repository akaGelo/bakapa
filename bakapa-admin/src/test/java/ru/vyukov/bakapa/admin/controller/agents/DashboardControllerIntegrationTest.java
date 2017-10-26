package ru.vyukov.bakapa.admin.controller.agents;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static com.codeborne.selenide.Selenide.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureStubRunner(ids = {"ru.vyukov:bakapa-controller:+:stubs:8080"}, workOffline = true)
public class DashboardControllerIntegrationTest extends  SuperUITest {



    @Test
    public void agents() throws Exception {
        open("/");
        $(".navbar-brand").shouldHave(Condition.text("Agents"));
    }


}
