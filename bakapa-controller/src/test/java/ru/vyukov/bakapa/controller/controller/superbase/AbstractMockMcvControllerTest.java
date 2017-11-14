package ru.vyukov.bakapa.controller.controller.superbase;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.config.SpringDataWebConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.vyukov.bakapa.controller.BakapaControllerApplication;
import ru.vyukov.bakapa.controller.service.agents.AgentNotFoundException;

/**
 * Test for  cloud contract verify
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BakapaControllerApplication.class)
@Import({WebMvcTestConfig.class, SpringDataWebConfiguration.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
abstract public class AbstractMockMcvControllerTest extends SuperControllerTest {



   @Autowired
    private MockMvc mockMvc;


    @Before
    public void setupMockMvc() throws AgentNotFoundException {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }



}
