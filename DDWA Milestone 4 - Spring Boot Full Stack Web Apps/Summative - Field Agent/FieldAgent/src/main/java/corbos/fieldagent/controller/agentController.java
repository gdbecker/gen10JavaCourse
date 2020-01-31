package corbos.fieldagent.controller;

import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.service.LookupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * M4 Summative
 * @author garrettbecker
 */

@Controller
public class agentController {
    @Autowired
    LookupService service;
    
    @GetMapping
    public String displayAgents(Model model) {
        List<Agent> agents = service.findAllAgents();
        model.addAttribute("agents", agents);
        
        return "index";
    }
}
