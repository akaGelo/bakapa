package ru.vyukov.bakapa.controller.service.agents;

import ru.vyukov.bakapa.controller.domain.Agent;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class DemoAgent {


    public static Agent newDemoAgent() {
        return new Agent("test","test", ZonedDateTime.now(),"test note");
    }
}
