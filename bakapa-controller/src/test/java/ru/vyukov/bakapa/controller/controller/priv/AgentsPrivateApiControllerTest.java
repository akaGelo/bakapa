package ru.vyukov.bakapa.controller.controller.priv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.vyukov.bakapa.controller.domain.Agent;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AgentsPrivateApiController.class)
@Import(value = WebMvcTestConfig.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class AgentsPrivateApiControllerTest {

    private final FieldDescriptor[] agentsFields = new FieldDescriptor[]{
            fieldWithPath("agentId").description("Unique agents Id (login)"),
            fieldWithPath("createDate").description("Create agents timestamp"),
            fieldWithPath("note").description("Free-form note. Optional").optional()
    };



    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private AgentsService agentsService;


    @Test
    public void getAllAgents() throws Exception {
        when(agentsService.getAllAgents()).thenReturn(asList(Agent.demo("test")));


        mockMvc.perform(get("/private/agents/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].password").doesNotExist())
                .andDo(document("agents",
                        responseFields().andWithPrefix("[*]", agentsFields))
                );
    }

    @Test
    public void createAgent() throws Exception {
        final String agentId = "agentIdTest";

        when(agentsService.newAgent(agentId)).thenReturn(Agent.demo("test"));

        mockMvc.perform(post("/private/agents/").param("agentId",agentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password").exists())
                .andDo(document("agents",
                        responseFields(agentsFields)
                        .and(fieldWithPath("password").description("Agent password"))

                        )
                );
    }

}