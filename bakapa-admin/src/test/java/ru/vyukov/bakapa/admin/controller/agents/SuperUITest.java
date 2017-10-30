package ru.vyukov.bakapa.admin.controller.agents;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.springframework.boot.context.embedded.LocalServerPort;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.MalformedURLException;

public abstract class SuperUITest {


    @LocalServerPort
    protected int serverPort;

    @PostConstruct
    public void init() throws MalformedURLException {
        if (0 == serverPort) {
            throw new IllegalStateException();
        }
        Configuration.baseUrl = "http://localhost:" + serverPort;
    }


    @PreDestroy
    public void destroy() {
        WebDriverRunner.closeWebDriver();
    }
}
