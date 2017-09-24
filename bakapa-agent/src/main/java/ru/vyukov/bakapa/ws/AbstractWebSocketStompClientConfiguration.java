package ru.vyukov.bakapa.ws;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import lombok.Data;

/**
 * Extends for use
 * @author gelo
 *
 */
public abstract class AbstractWebSocketStompClientConfiguration {

	@Data
	@ConfigurationProperties("admin.ws")
	@Component
	public static class WebSocketStompClientProperties {
		@NotNull
		private String url;

	}

	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	StomSubsribeAnnotationBeanPostProcessor stomSubsribeAnnotationBeanPostProcessor() {
		return new StomSubsribeAnnotationBeanPostProcessor();
	}

	@Bean
	WebSocketStompClient webSocketClient(WebSocketStompClientProperties clientProperties) {
		WebSocketClient webSocketClient = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new ReconectableWebSocketStompClient(webSocketClient, taskScheduler(),
				5_000);
		stompClient.setMessageConverter(messageConverter());
		stompClient.connect(clientProperties.getUrl(), sessionHandler());
		return stompClient;
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
		return new MappingJackson2MessageConverter();
	}
}
