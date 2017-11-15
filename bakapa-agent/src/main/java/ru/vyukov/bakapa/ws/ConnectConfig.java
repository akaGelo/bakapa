package ru.vyukov.bakapa.ws;

import lombok.Data;
import lombok.Value;
import org.apache.commons.codec.binary.Base64;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.socket.WebSocketHttpHeaders;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

@Value
class ConnectConfig {

    private long reconnectDelay;

    private String url;

    private WebSocketHttpHeaders handshakeHeaders;

    private StompSessionHandler sessionHandler;


    public ConnectConfig(WebSocketStompClientProperties properties, StompSessionHandler sessionHandler) {
        this.url = properties.getUrl();
        this.sessionHandler = sessionHandler;
        this.handshakeHeaders = properties.getHeaders();
        this.reconnectDelay = properties.getReconnectDelay();
    }
}
