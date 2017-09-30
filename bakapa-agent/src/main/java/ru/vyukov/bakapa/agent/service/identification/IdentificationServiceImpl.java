package ru.vyukov.bakapa.agent.service.identification;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class IdentificationServiceImpl implements IdentificationService {

	
	//тут же хранится инфа для доступа к серверу
	
	public IdentificationServiceImpl() {
		// создать id или прочитать его
	}
	
	
	@Scheduled(fixedDelay=3_000)
	public void shedule() {
		
	}
}
