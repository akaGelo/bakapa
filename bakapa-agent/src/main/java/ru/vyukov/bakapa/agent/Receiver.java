package ru.vyukov.bakapa.agent;

import org.springframework.stereotype.Component;
import ru.vyukov.stomp.Subscribe;

import javax.annotation.PostConstruct;

@Component
public class Receiver {

    @PostConstruct
    public void init() {
        System.err.println("");
    }

    @Subscribe("/topic/greetings")
    public void receive(Greeting payload) {
        System.err.println(payload);
    }

}
