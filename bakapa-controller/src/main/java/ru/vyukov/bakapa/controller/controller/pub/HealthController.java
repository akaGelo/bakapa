package ru.vyukov.bakapa.controller.controller.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
import ru.vyukov.bakapa.controller.Greeting;

import java.security.Principal;

/**
 * @author gelo
 */
@RestController
public class HealthController {

    @Autowired
    private SimpMessageSendingOperations messageSendingOperations;

    @MessageMapping("/health")
    public void getCredentials(Principal principal, String  payload) {
        System.err.println(payload);
        // return new Greeting("test");
//		messageSendingOperations.convertAndSendToUser(principal.getName(), "/topic/greetings", new Greeting("test"));
        // messageSendingOperations.convertAndSend("/user/test/topic/greetings", new
        // Greeting("test"));
    }

}