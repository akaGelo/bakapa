package ru.vyukov.bakapa.controller.controller.pub;

import java.security.Principal;

import org.bakapa.dto.RequestCredentialsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import ru.vyukov.bakapa.controller.Greeting;

/**
 * 
 * @author gelo
 *
 */
@RestController
public class HealthController {

	@Autowired
	private SimpMessageSendingOperations messageSendingOperations;

	@MessageMapping("/health")
	// @SendTo("/topic/greetings")
	public void getCredentials(Principal principal, Greeting greeting) {
		System.err.println(greeting);
		// return new Greeting("test");
		messageSendingOperations.convertAndSendToUser(principal.getName(), "/topic/greetings", new Greeting("test"));
		// messageSendingOperations.convertAndSend("/user/test/topic/greetings", new
		// Greeting("test"));
	}

}