package ru.vyukov.bakapa.admin.controller.agents;

import org.bakapa.dto.agent.AgentAndCredentialsDTO;
import org.bakapa.dto.agent.AgentAndInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vyukov.bakapa.admin.controller.SuperUIController;
import ru.vyukov.bakapa.admin.controller.agents.pojo.NewAgent;
import ru.vyukov.bakapa.admin.service.agents.AgentsApiClient;

import java.util.List;

@Controller
@RequestMapping("/agents")
public class AgentsController extends SuperUIController {

    @Autowired
    private AgentsApiClient agentsApiClient;


    @ModelAttribute("newAgent")
    NewAgent newAgent() {
        return new NewAgent();
    }


    @GetMapping("/")
    public String agents(Model model) {
        List<AgentAndInfoDTO> agentsAndInfo = agentsApiClient.getAgents();
        model.addAttribute("agentsAndInfo", agentsAndInfo);
        return "agents/agents";
    }

    @PostMapping("/")
    public String createAgent(@Validated NewAgent newAgent, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            dangerMessage(model,bindingResult);
            return "agents/agents";
        }
        //redirect
        AgentAndCredentialsDTO agentAndCredentialsDTO = agentsApiClient.create(newAgent.getAgentId());
        redirectAttributes.addFlashAttribute("newAgent", agentAndCredentialsDTO);
        return "redirect:/agents/new/";
    }


    static public String redirect() {
        return "redirect:/agents/";
    }

}
