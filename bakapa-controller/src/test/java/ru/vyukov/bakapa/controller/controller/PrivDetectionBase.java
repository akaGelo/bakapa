package ru.vyukov.bakapa.controller.controller;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.vyukov.bakapa.controller.BakapaControllerApplication;
import ru.vyukov.bakapa.controller.config.SecurityConfig;
import ru.vyukov.bakapa.controller.controller.priv.AgentsPrivateApiController;
import ru.vyukov.bakapa.controller.controller.priv.DetectionPrivateApiController;
import ru.vyukov.bakapa.controller.domain.Agent;
import ru.vyukov.bakapa.controller.domain.DetectionConfig;
import ru.vyukov.bakapa.controller.service.agents.AgentNotFoundException;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.detection.DetectionService;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AgentsPrivateApiController.class)
@Import(WebMvcTestConfig.class)
@ContextConfiguration(classes = DetectionPrivateApiController.class)
@FixMethodOrder
public class PrivDetectionBase {

    @MockBean
    private DetectionService detectionService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() throws AgentNotFoundException {

        when(detectionService.getDetectionConfig()).thenReturn(DetectionConfig.defaultConfig());

        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    public void assertThatRejectionReasonIsNull(Object o) {
        System.err.println("\n\n\n" + o + "\n\n\n");
    }
}
