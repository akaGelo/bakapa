package ru.vyukov.bakapa.agent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BakapaAgentApplicationTests {

    @MockBean
    private SimpMessagingTemplate simpMessagingTemplate;

    @Test
    public void contextLoads() {
    }

}
