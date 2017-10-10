package ru.vyukov.bakapa.admin.controller.agents;

import org.bakapa.dto.agent.AgentAndCredentialsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/agents")
public class NewAgentController extends  SuperUIController{


    @GetMapping("/new/")
    public String  newAgent(@ModelAttribute("newAgent")AgentAndCredentialsDTO newAgent){

        return "agents/new-agent";
    }
}
