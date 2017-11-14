package ru.vyukov.bakapa.admin.controller;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.MalformedURLException;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class SuperUIMockTest {


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
