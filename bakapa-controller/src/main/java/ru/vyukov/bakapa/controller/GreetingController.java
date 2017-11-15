package ru.vyukov.bakapa.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(String message) throws Exception {
        log.info("\n\n\nReceived hello: {}\n\n\n", message);
        return new Greeting("Hello, " + message + "!");
    }


}