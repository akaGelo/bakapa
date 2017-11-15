package ru.vyukov.bakapa.ws;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHttpHeaders;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

@Data
public class WebSocketStompClientProperties {


    private long reconnectDelay = 5_000;

    @NotNull
    private String url;


    @Valid
    private BasicAuth basicAuth = new BasicAuth();

    @Data
    class BasicAuth {

        @Nullable
        private String username;

        @Nullable
        private String password;

        public boolean isNotEmpty() {
            return null != username && null != password;
        }
    }


    public WebSocketHttpHeaders getHeaders() {
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        if (basicAuth.isNotEmpty()) {
            String plainCreds = basicAuth.getUsername() + ":" + basicAuth.getPassword();
            String base64Creds = new String(encodeBase64(plainCreds.getBytes()));
            headers.add("Authorization", "Basic " + base64Creds);
        }
        return headers;
    }


}
