package ru.vyukov.bakapa.controller.domain.backup.target;

import org.junit.Before;
import ru.vyukov.bakapa.controller.domain.agent.Agent;

import static org.junit.Assert.assertNotEquals;

abstract public class AgentChangeTest {

    protected final Agent agentOne = Agent.demo("One");
    protected final Agent agentTwo = Agent.demo("Two");

    @Before
    public void agentsCheck() {
        assertNotEquals(agentOne, agentTwo);
    }
}
