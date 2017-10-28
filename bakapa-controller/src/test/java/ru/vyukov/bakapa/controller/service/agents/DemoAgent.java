package ru.vyukov.bakapa.controller.service.agents;

import ru.vyukov.bakapa.controller.domain.agent.Agent;

import java.time.Instant;

public class DemoAgent {


    public static Agent newDemoAgent() {
        return new Agent("test","test", Instant.now(),"test note");
    }
}
