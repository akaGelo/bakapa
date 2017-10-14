package ru.vyukov.bakapa.controller.service.agents;

public class AgentNotFoundException extends Exception {
    public AgentNotFoundException(String agentId) {
        super("Agent [" + agentId + "] not found");
    }
}
