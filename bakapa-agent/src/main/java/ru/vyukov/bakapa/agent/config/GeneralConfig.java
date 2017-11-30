package ru.vyukov.bakapa.agent.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import sun.management.ManagementFactoryHelper;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * @author Oleg Vyukov
 */
@Configuration
public class GeneralConfig {

    @Bean
    public OperatingSystemMXBean operatingSystemMXBean() {
        return ManagementFactoryHelper.getOperatingSystemMXBean();
    }
}
