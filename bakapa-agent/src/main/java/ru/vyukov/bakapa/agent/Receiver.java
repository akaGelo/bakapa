package ru.vyukov.bakapa.agent;

import ru.vyukov.bakapa.ws.StomReceiver;
import ru.vyukov.bakapa.ws.Subscribe;

import javax.annotation.PostConstruct;

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
