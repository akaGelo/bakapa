package ru.vyukov.bakapa.controller.config;

import static java.util.Arrays.asList;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class GeneralConfig {

	@Bean
	ModelMapper modelMapper(){
		return new ModelMapper();
	}
	
	@Bean
	HttpMessageConverters httpMessageConverters(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
		return new HttpMessageConverters(false,asList( mappingJackson2HttpMessageConverter ));
	}
}
