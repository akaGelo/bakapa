package ru.vyukov.bakapa.controller.service.agents;

import ru.vyukov.bakapa.controller.domain.Agent;

import java.time.LocalDateTime;

public class DemoAgent {


    public static Agent newDemoAgent() {
        return new Agent("test","test", LocalDateTime.now(),"test note");
    }
}
