package ru.vyukov.bakapa.controller.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.vyukov.bakapa.controller.config.GeneralConfig;
import ru.vyukov.bakapa.controller.config.SecurityConfig;

@Configuration
@Import(SecurityConfig.class)
public class WebMvcTestConfig extends GeneralConfig {



}
