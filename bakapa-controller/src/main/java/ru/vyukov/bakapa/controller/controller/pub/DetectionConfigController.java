package ru.vyukov.bakapa.controller.controller.pub;

import static org.bakapa.dto.DetectionConfigDTO.PATH_DETECTION_CONFIG;

import java.security.Principal;

import org.bakapa.dto.DetectionConfigDTO;
import org.bakapa.dto.RequestCredentialsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
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
public class DetectionConfigController {

	@MessageMapping(PATH_DETECTION_CONFIG)
	@SendToUser(PATH_DETECTION_CONFIG)
	public Greeting getCredentials(Principal principal, Greeting greeting) {
		System.err.println(greeting);
		// return new Greeting("test");
		return new Greeting("ok");
	}

}