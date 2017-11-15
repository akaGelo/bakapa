package ru.vyukov.bakapa.ws;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

/**
 * Extends for use
 *
 * @author gelo
 */
@Configuration
public class AbstractWebSocketStompClientConfiguration {


    @Bean
    @ConfigurationProperties("controller")
    public WebSocketStompClientProperties wsConfig() {
        return new WebSocketStompClientProperties();
    }

    @Bean
    SimpMessagingTemplate simpMessagingTemplate() {
        SimpMessagingTemplate simpMessagingTemplate = new SimpMessagingTemplate(stompMessageChannel());
        simpMessagingTemplate.setMessageConverter(messageConverter());
        return simpMessagingTemplate;
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    StompSubscribeAnnotationBeanPostProcessor stompSubsribeAnnotationBeanPostProcessor() {
        return new StompSubscribeAnnotationBeanPostProcessor();
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    StompMessageChannel stompMessageChannel() {
        WebSocketClient webSocketClient = new StandardWebSocketClient();

        ConnectConfig config = new ConnectConfig(wsConfig(), sessionHandler());
        StompMessageChannel stompMessageChannel = new StompMessageChannel(webSocketClient, taskScheduler(), messageConverter(), config);

        return stompMessageChannel;
    }

    @Bean
    StompSessionHandler sessionHandler() {
        StompSessionHandler sessionHandler = new SubscribeMethodsInvokerSessionHandler();
        return sessionHandler;
    }

    @Bean
    TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

    @Bean
    MessageConverter messageConverter() {
        MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        return mappingJackson2MessageConverter;
    }


}
