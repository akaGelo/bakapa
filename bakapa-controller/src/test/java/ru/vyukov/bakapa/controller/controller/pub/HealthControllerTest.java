package ru.vyukov.bakapa.controller.controller.pub;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSession.Subscription;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import ru.vyukov.bakapa.controller.Greeting;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HealthControllerTest {

	@Value("${local.server.port}")
	private int port;
	private String URL;

	private CompletableFuture<Greeting> completableFuture;

	@Before
	public void setup() {
		completableFuture = new CompletableFuture<>();
		URL = "ws://localhost:" + port + "/public/websocket";
	}

	@Test
	public void tvestCreateGameEndpoint() throws Exception {
		WebSocketClient webSocketClient = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		WebSocketHttpHeaders handshakeHeaders = new WebSocketHttpHeaders();
		String auth = "test" + ":" + "test";
		handshakeHeaders.add(WebSocketHttpHeaders.AUTHORIZATION,
				"Basic " + new String(Base64.getEncoder().encode(auth.getBytes())));

		StompSession stompSession = stompClient.connect(URL, handshakeHeaders, new StompSessionHandlerAdapter() {
		}).get(1, SECONDS);

		Subscription subscribe = stompSession.subscribe("/user/topic/greetings", new CreateGameStompFrameHandler());
		
		
		stompSession.send("/app/health", new Greeting());

		Greeting gameState = completableFuture.get(10, SECONDS);

		assertNotNull(gameState);
	}
	
	@Test
	public void testFailAuth() {
		fail("not implemented");
	}

	private class CreateGameStompFrameHandler implements StompFrameHandler {
		@Override
		public Type getPayloadType(StompHeaders stompHeaders) {
			return Greeting.class;
		}

		@Override
		public void handleFrame(StompHeaders stompHeaders, Object o) {
			completableFuture.complete((Greeting) o);
		}

	}

}
