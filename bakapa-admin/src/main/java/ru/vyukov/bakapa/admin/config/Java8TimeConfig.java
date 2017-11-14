package ru.vyukov.bakapa.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vyukov.time.Java8InstantDialect;

@Configuration
public class Java8TimeConfig {

    @Bean
    Java8InstantDialect fixJava8TimeDialect() {
        return new Java8InstantDialect();
    }
}
