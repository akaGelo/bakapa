package ru.vyukov.bakapa.controller.config;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import static java.util.Arrays.asList;

@Configuration
public class GeneralConfig {

    @Bean
    HttpMessageConverters httpMessageConverters(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
        return new HttpMessageConverters(false, asList(mappingJackson2HttpMessageConverter));
    }


}
