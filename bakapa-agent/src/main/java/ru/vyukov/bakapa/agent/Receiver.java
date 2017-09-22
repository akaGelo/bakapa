package ru.vyukov.bakapa.agent;

import javax.annotation.PostConstruct;

import ru.vyukov.bakapa.agent.ws.StomReceiver;
import ru.vyukov.bakapa.agent.ws.Subscribe;

@StomReceiver
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
