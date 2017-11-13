package ru.vyukov.bakapa.controller.controller.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;
import ru.vyukov.bakapa.controller.Greeting;
import ru.vyukov.bakapa.controller.service.backups.BackupsLogsService;

import java.security.Principal;

import static ru.vyukov.bakapa.dto.config.DetectionConfigDTO.PATH_DETECTION_CONFIG;

/**
 * @author gelo
 */
@RestController
public class EventLogController {

    @Autowired
    private BackupsLogsService backupsLogsService;

    //TODO save log events

//	@MessageMapping(PATH_DETECTION_CONFIG)
//	@SendToUser(PATH_DETECTION_CONFIG)
//	public Greeting getCredentials(Principal principal, Greeting greeting) {
//		System.err.println(greeting);
//		// return new Greeting("test");
//		return new Greeting("ok");
//	}

}