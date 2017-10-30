package ru.vyukov.bakapa.ws;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * This session handler invoke @Subscribe methods on receive messages
 * 
 * @author gelo
 *
 */
@Slf4j
public class SubscribeMethodsInvokerSessionHandler extends StompSessionHandlerAdapter {

	@Setter
	private Map<String, SubscribeMethodInstance> subscribeMethodInstances;

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
//		for (SubscribeMethodInstance smi : subscribeMethodInstances.values()) {
//			session.subscribe(smi.getDestination(), SubscribeMethodsInvokerSessionHandler.this);
//		}
		log.info("New session: {}", session.getSessionId());
		
	     session.send("/app/hello", "{\"name\":\"Client\"}".getBytes());

	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
			Throwable exception) {
		log.error("webSocket error", exception);
	}

	@Override
	public void handleTransportError(StompSession session, Throwable exception) {
		log.error("webSocket transport error", exception);
	}

	@Override
	public Type getPayloadType(StompHeaders headers) {
		String destination = headers.getDestination();

		SubscribeMethodInstance first = subscribeMethodInstances.get(destination);
		if (null == first) {
			throw new NullPointerException("No method for " + destination);
		}

		return first.getArgType();
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		String destination = headers.getDestination();
		SubscribeMethodInstance subscribeMethodInstance = subscribeMethodInstances.get(destination);
		try {
			subscribeMethodInstance.invoke(payload);
		} catch (NullPointerException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			log.error("Stomp invoke exception", e);
		}
	}
}