package ru.vyukov.bakapa.controller.controller.pub;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;

import javax.websocket.DeploymentException;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HealthControllerTest {

    private Agent agent1 = Agent.demo("agent1");

    private Agent agentWrongPassword = Agent.demo("agentWrongPassword");

    @Value("${local.server.port}")
    private int port;

    private String URL;

//    private CompletableFuture<Greeting> completableFuture;

    @MockBean
    private AgentsService agentsService;

    @Before
    public void setup() {
        when(agentsService.loadUserByUsername("agent1")).thenReturn(agent1);

        when(agentsService.loadUserByUsername("agentWrongPassword")).thenReturn(agentWrongPassword);

//        completableFuture = new CompletableFuture<>();
        URL = "ws://localhost:" + port + "/public/websocket";
    }


    @Test
    public void testAuth() throws Exception {
        StompSession stompSession = newSession(agent1);

        assertNotNull(stompSession);
        assertTrue(stompSession.isConnected());
    }


    @Test
    public void testFailAuth() throws Exception {
        try {
            StompSession stompSession = newSession(agentWrongPassword.getUsername(), "wrong");
            fail("expected 401");
        } catch (ExecutionException e) {
            DeploymentException cause = (DeploymentException) e.getCause();
            assertEquals("The HTTP response from the server [401] did not permit the HTTP upgrade to WebSocket",
                    cause.getMessage());
        }
    }

    @Test
    @Ignore("subscribe test example")
    public void testSubscribe() throws Exception {
//        StompSession stompSession = newSession(agent1.getUsername(), agent1.getPassword());
//
//        Subscription subscribe = stompSession.subscribe("/user/topic/greetings", new CreateGameStompFrameHandler());
//
//
//        stompSession.send("/app/health", new Greeting());
//
//        Greeting gameState = completableFuture.get(10, SECONDS);
//
//        assertNotNull(gameState);
    }


    private StompSession newSession(Agent agent) throws Exception {
        return newSession(agent.getUsername(), agent.getPassword());
    }

    private StompSession newSession(String login, String password) throws Exception {
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        WebSocketHttpHeaders handshakeHeaders = new WebSocketHttpHeaders();
        String auth = login + ":" + password;
        handshakeHeaders.add(WebSocketHttpHeaders.AUTHORIZATION,
                "Basic " + new String(Base64.getEncoder().encode(auth.getBytes())));

        StompSession stompSession = stompClient.connect(URL, handshakeHeaders, new StompSessionHandlerAdapter() {
        }).get(1, SECONDS);
        return stompSession;
    }


//
//    private class CreateGameStompFrameHandler implements StompFrameHandler {
//        @Override
//        public Type getPayloadType(StompHeaders stompHeaders) {
//            return Greeting.class;
//        }
//
//        @Override
//        public void handleFrame(StompHeaders stompHeaders, Object o) {
//            completableFuture.complete((Greeting) o);
//        }
//
//    }

}
