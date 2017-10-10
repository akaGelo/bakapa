package ru.vyukov.bakapa.controller.controller.pub;

import static org.bakapa.dto.detection.DetectionConfigDTO.PATH_DETECTION_CONFIG;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
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