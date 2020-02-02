package corbos.fieldagent.controller;

import corbos.fieldagent.entities.Agency;
import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.entities.Assignment;
import corbos.fieldagent.entities.Country;
import corbos.fieldagent.entities.SecurityClearance;
import corbos.fieldagent.service.LookupService;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * M4 Summative
 * @author garrettbecker
 */

@Controller
public class agentAndAssignmentController {
    @Autowired
    LookupService service;
    
    Set<ConstraintViolation<Agent>> violations = new HashSet<>();
    
    @GetMapping
    public String displayAgents(Model model) {
        List<Agent> agents = service.findAllAgents();
        model.addAttribute("agents", agents);
        
        return "index";
    }
    
    @GetMapping("addAgent")
    public String loadAddAgentPage(Model model) {
        List<Agency> agencies = service.findAllAgencies();
        model.addAttribute("agencies", agencies);
        
        List<SecurityClearance> clearances = service.findAllSecurityClearances();
        model.addAttribute("clearances", clearances);
        
        model.addAttribute("errors", violations);
        
        return "addAgent";
    }
    
    @PostMapping("addNewAgent")
    public String addNewAgent(HttpServletRequest request) {
        Agent agent = new Agent();
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        String height = request.getParameter("height");
        String identifier = request.getParameter("identifier");
        String agencyId = request.getParameter("agencyId");
        String clearanceId = request.getParameter("clearanceId");
        String activationDate = request.getParameter("activationDate");
        String isActive = request.getParameter("isActive");
        String pictureUrl = request.getParameter("pictureUrl");

        agent.setFirstName(firstName);
        agent.setMiddleName(middleName);
        agent.setLastName(lastName);
        agent.setBirthDate(LocalDate.parse(birthDate));
        agent.setHeight(Integer.parseInt(height));
        agent.setIdentifier(identifier);
        agent.setAgency(service.findAgencyByAgencyId(Integer.parseInt(agencyId)));
        agent.setSecurityClearance(service.findSecurityClearanceById(Integer.parseInt(clearanceId)));
        agent.setActivationDate(LocalDate.parse(activationDate));
        agent.setActive(Boolean.getBoolean(isActive));
        agent.setPictureUrl(pictureUrl);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(agent);

        if(violations.isEmpty()) {
            service.addUpdateAgent(agent);
            return "redirect:/";
        }
        
        return "redirect:/addAgent";
    }
    
    @PostMapping("editAgent")
    public String editAgent(HttpServletRequest request, Model model) {
        Agent agent = new Agent();
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        String height = request.getParameter("height");
        String identifier = request.getParameter("identifier");
        String agencyId = request.getParameter("agencyId");
        String clearanceId = request.getParameter("clearanceId");
        String activationDate = request.getParameter("activationDate");
        String isActive = request.getParameter("isActive");
        String pictureUrl = request.getParameter("pictureUrl");

        agent.setFirstName(firstName);
        agent.setMiddleName(middleName);
        agent.setLastName(lastName);
        agent.setBirthDate(LocalDate.parse(birthDate));
        agent.setHeight(Integer.parseInt(height));
        agent.setIdentifier(identifier);
        agent.setAgency(service.findAgencyByAgencyId(Integer.parseInt(agencyId)));
        agent.setSecurityClearance(service.findSecurityClearanceById(Integer.parseInt(clearanceId)));
        agent.setActivationDate(LocalDate.parse(activationDate));
        agent.setActive(Boolean.valueOf(isActive));
        agent.setPictureUrl(pictureUrl);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(agent);

        if(violations.isEmpty()) {
            service.addUpdateAgent(agent);
            return "redirect:/";
        }
        
        return "redirect:/viewEditAgent?id=" + identifier;
    }
    
    @GetMapping("viewEditAgent")
    public String agentDetails(String id, Model model) {
        Agent agent = service.findAgentById(id);
        model.addAttribute("agent", agent);
        
        List<Agency> agencies = service.findAllAgencies();
        model.addAttribute("agencies", agencies);
        
        List<SecurityClearance> clearances = service.findAllSecurityClearances();
        model.addAttribute("clearances", clearances);
        
        List<Assignment> assignments = service.findAssignmentByAgentIdentifier(id);
        model.addAttribute("assignments", assignments);
        
        model.addAttribute("errors", violations);
        
        return "viewEditAgent";
    }
    
    @GetMapping("deleteAgentPage")
    public String deleteAgentPage(String id, Model model) {
        Agent agent = service.findAgentById(id);
        model.addAttribute("agent", agent);
        
        List<Assignment> assignments = service.findAssignmentByAgentIdentifier(id);
        int num = assignments.size();
        model.addAttribute("num", num);
        
        return "deleteAgent";
    }
    
    @GetMapping("deleteAgentInfo")
    public String deleteAgentInfo(String id) {
        List<Assignment> assignments = service.findAssignmentByAgentIdentifier(id);
        
        for(Assignment a : assignments) {
            service.deleteAssignment(a);
        }
        
        service.deleteAgentById(id);
        return "redirect:/";
    }
    
    @GetMapping("addAssignment")
    public String loadAddAssignmentPage(String id, Model model) {
        List<Agent> agents = service.findAllAgents();
        model.addAttribute("agents", agents);
        
        Agent agent = service.findAgentById(id);
        model.addAttribute("foundAgent", agent);
        
        List<Country> countries = service.findAllCountries();
        model.addAttribute("countries", countries);
        
        return "addAssignment";
    }
    
    @PostMapping("addNewAssignment")
    public String addNewAssignment(HttpServletRequest request) {
        Assignment a = new Assignment();
        String agentId = request.getParameter("agentId");
        String countryId = request.getParameter("countryId");
        String startDate = request.getParameter("startDate");
        String projectedEndDate = request.getParameter("projectedEndDate");
        String actualEndDate = request.getParameter("actualEndDate");
        String notes = request.getParameter("notes");
        
        a.setAgent(service.findAgentById(agentId));
        a.setCountry(service.findCountryByCode(countryId));
        a.setStartDate(LocalDate.parse(startDate));
        a.setProjectedEndDate(LocalDate.parse(projectedEndDate));
        
        if (actualEndDate.equals(null)) {
            a.setActualEndDate(null);
        }
        
        if (!actualEndDate.equals("")) {
            a.setActualEndDate(LocalDate.parse(actualEndDate));
        }
           
        a.setNotes(notes);
        service.addUpdateAssignement(a);
        
        return "redirect:/";
    }
    
    @PostMapping("editAssignment")
    public String editAssignment(HttpServletRequest request) {
        Assignment a = new Assignment();
        //String assignmentId = request.getParameter("assignmentId");
        String agentId = request.getParameter("agentId");
        String countryId = request.getParameter("countryId");
        String startDate = request.getParameter("startDate");
        String projectedEndDate = request.getParameter("projectedEndDate");
        String actualEndDate = request.getParameter("actualEndDate");
        String notes = request.getParameter("notes");
        
        //a.setAssignmentId(Integer.parseInt(assignmentId));
        a.setAgent(service.findAgentById(agentId));
        a.setCountry(service.findCountryByCode(countryId));
        a.setStartDate(LocalDate.parse(startDate));
        a.setProjectedEndDate(LocalDate.parse(projectedEndDate));
        
        if (actualEndDate.equals("")) {
            a.setActualEndDate(null);
        }
        
        if (!actualEndDate.equals("")) {
            a.setActualEndDate(LocalDate.parse(actualEndDate));
        }
           
        a.setNotes(notes);
        service.addUpdateAssignement(a);
        
        return "redirect:/viewEditAgent?id=" + agentId;
    }
    
    @GetMapping("viewEditAssignment")
    public String assignmentDetails(int id, Model model) {
        Assignment a = service.findAssignmentById(id);
        model.addAttribute("assignment", a);
        
        Agent agentAssigned = a.getAgent();
        model.addAttribute("foundAgent", agentAssigned);
        
        List<Agent> agents = service.findAllAgents();
        model.addAttribute("agents", agents);
        
        List<Country> countries=service.findAllCountries();
        model.addAttribute("countries", countries);
        
        return "viewEditAssignment";
    }
    
    @GetMapping("deleteAssignmentPage")
    public String deleteAssignmentPage(int id, Model model) {
        Assignment a = service.findAssignmentById(id);
        model.addAttribute("assignment", a); 
        
        Agent agentAssigned = a.getAgent();
        model.addAttribute("agent", agentAssigned);
        
        return "deleteAssignment";
    }
    
    @GetMapping("deleteAssignmentInfo")
    public String deleteAssignmentInfo(int id, Model model) {
        Assignment a = service.findAssignmentById(id);
        Agent agent = a.getAgent();
        String agentId = agent.getIdentifier();
        
        service.deleteAssignment(a);
        return "redirect:/viewEditAgent?id=" + agentId;
    }
}
