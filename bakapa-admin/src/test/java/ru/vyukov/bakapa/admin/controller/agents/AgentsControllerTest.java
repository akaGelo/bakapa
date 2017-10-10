package ru.vyukov.bakapa.admin.controller.agents;

import org.aspectj.weaver.loadtime.Agent;
import org.bakapa.dto.agent.AgentAndCredentialsDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.vyukov.bakapa.admin.service.agents.AgentsApiClient;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AgentsController.class)
public class AgentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgentsApiClient agentsApiClient;


    @Test
    public void createAgent() throws Exception {
        final String agentId = "agentId";
        when(agentsApiClient.create(any(String.class))).thenReturn(AgentAndCredentialsDTO.demo("test"));

        mockMvc.perform((post("/agents/")).param(agentId,""))
                .andExpect(view().name("agents/agents"));


        mockMvc.perform((post("/agents/")).param(agentId,"notEmpty"))
                .andExpect(status().is3xxRedirection());

        verify(agentsApiClient,only()).create(any(String.class));
    }

}