package ru.vyukov.bakapa.admin.controller.agents;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/agents")
public class AgentsController {

    @GetMapping("/")
    public String agents() {
        return "agents/agents";
    }


}
