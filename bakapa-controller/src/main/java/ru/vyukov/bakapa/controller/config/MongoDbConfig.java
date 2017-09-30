package ru.vyukov.bakapa.controller.config;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;

@Configuration
public class MongoDbConfig {

	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener(Validator validator) {
		return new ValidatingMongoEventListener(validator);
	}

}
