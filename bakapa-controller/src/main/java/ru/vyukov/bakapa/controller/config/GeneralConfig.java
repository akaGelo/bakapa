package ru.vyukov.bakapa.controller.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import ru.vyukov.bakapa.jackson.PageSerializer;

import static java.util.Arrays.asList;

@Configuration
public class GeneralConfig {

    @Bean
    HttpMessageConverters httpMessageConverters(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
        return new HttpMessageConverters(false, asList(mappingJackson2HttpMessageConverter));
    }


    @Bean
    public Module jacksonPageWithJsonViewModule() {
        SimpleModule module = new SimpleModule("jackson-page-with-jsonview", Version.unknownVersion());
        module.addSerializer(PageImpl.class, new PageSerializer());
        return module;
    }





}
