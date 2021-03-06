package corbos.fieldagent.controller;

import corbos.fieldagent.entities.Agency;
import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.entities.Assignment;
import corbos.fieldagent.entities.Country;
import corbos.fieldagent.entities.SecurityClearance;
import corbos.fieldagent.service.LookupService;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    
    Set<ConstraintViolation<Agent>> agentAddViolations = new HashSet<>();
    Set<ConstraintViolation<Agent>> agentEditViolations = new HashSet<>();
    Set<ConstraintViolation<Assignment>> assignmentAddViolations = new HashSet<>();
    Set<ConstraintViolation<Assignment>> assignmentEditViolations = new HashSet<>();
    
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
        
        model.addAttribute("errors", agentAddViolations);
        
        return "addAgent";
    }
    
    @PostMapping("addNewAgent")
    public String addNewAgent(HttpServletRequest request, Model model) {
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
        String isActiveString = request.getParameter("isActive");
        Boolean isActive;
        
        if (isActiveString == null) {
            isActive = false;
        } else {
            isActive = true;
        }
        
        String pictureUrl = request.getParameter("pictureUrl");

        //Add values to the Agent
        agent.setFirstName(firstName);
        agent.setMiddleName(middleName);
        agent.setLastName(lastName);
        
        try {
            agent.setBirthDate(LocalDate.parse(birthDate));
            
            LocalDate dayOfBirth = LocalDate.parse(birthDate);
            LocalDate afterDate = LocalDate.parse("1900-01-01");
            LocalDate beforeDate = LocalDate.now().minusYears(10);
            
            if (dayOfBirth.isAfter(afterDate) && dayOfBirth.isBefore(beforeDate)) {
                agent.setBirthDate(LocalDate.parse(birthDate));
            } else {
                agent.setBirthDate(null);
            }
        } catch (DateTimeParseException e) {
            agent.setBirthDate(null);
        }
        
        if (height.equals("")) {
            agent.setHeight(0);
        } else {
            try {
                agent.setHeight(Integer.parseInt(height));
            } catch (NumberFormatException e) {
                agent.setHeight(0);
            }
        }
        
        agent.setIdentifier(identifier);
        agent.setAgency(service.findAgencyByAgencyId(Integer.parseInt(agencyId)));
        agent.setSecurityClearance(service.findSecurityClearanceById(Integer.parseInt(clearanceId)));
        
        try {
            agent.setActivationDate(LocalDate.parse(activationDate));
        } catch (DateTimeParseException e) {
            agent.setActivationDate(null);
        }
        
        agent.setActive(isActive);
        agent.setPictureUrl(pictureUrl);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        agentAddViolations = validate.validate(agent);
        
        boolean hasErrors;
        if (agentAddViolations.isEmpty()) {
            hasErrors = false;
        } else {
            hasErrors = true;
        }
        
        //Go back to Home only if there were no errors with the form
        if(!hasErrors) {
            service.addUpdateAgent(agent);
            return "redirect:/";
        }
        
        
        return "redirect:/addAgent";
    }
    
    @GetMapping("clearAddAgentErrors")
    public String clearAddAgentErrors() {
        agentAddViolations.clear();
        return "redirect:/";
    }
    
    @GetMapping("viewEditAgent")
    public String agentDetails(String id, Model model) {
        //Load the Agent into the view & edit details page
        Agent agent = service.findAgentById(id);
        model.addAttribute("agent", agent);
        
        //Get the isActive value from the Agent, assign on the page
        Boolean isActive = agent.isActive();
        String isActiveString;
        if (isActive == true) {
            isActiveString = "1";
            model.addAttribute("isActive", isActive);
        } else {
            isActiveString = "0";
            model.addAttribute("isActive", isActive);
        }
        
        //model.addAttribute("isActive", isActiveString);
        
        List<Agency> agencies = service.findAllAgencies();
        model.addAttribute("agencies", agencies);
        
        List<SecurityClearance> clearances = service.findAllSecurityClearances();
        model.addAttribute("clearances", clearances);
        
        List<Assignment> assignments = service.findAssignmentByAgentIdentifier(id);
        model.addAttribute("assignments", assignments);
        
        model.addAttribute("errors", agentEditViolations);
        
        return "viewEditAgent";
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
        String isActiveString = request.getParameter("isActive");
        Boolean isActive;
        
        if (isActiveString == null) {
            isActive = false;
        } else {
            isActive = true;
        }
        
        String pictureUrl = request.getParameter("pictureUrl");

        agent.setFirstName(firstName);
        agent.setMiddleName(middleName);
        agent.setLastName(lastName);
        
        try {
            agent.setBirthDate(LocalDate.parse(birthDate));
            
            LocalDate dayOfBirth = LocalDate.parse(birthDate);
            LocalDate afterDate = LocalDate.parse("1900-01-01");
            LocalDate beforeDate = LocalDate.now().minusYears(10);
            
            if (dayOfBirth.isAfter(afterDate) && dayOfBirth.isBefore(beforeDate)) {
                agent.setBirthDate(LocalDate.parse(birthDate));
            } else {
                agent.setBirthDate(null);
            }
        } catch (DateTimeParseException e) {
            agent.setBirthDate(null);
        }
        
        if (height.equals("")) {
            agent.setHeight(0);
        } else {
            try {
                agent.setHeight(Integer.parseInt(height));
            } catch (NumberFormatException e) {
                agent.setHeight(0);
            }
        }
        
        agent.setIdentifier(identifier);
        agent.setAgency(service.findAgencyByAgencyId(Integer.parseInt(agencyId)));
        agent.setSecurityClearance(service.findSecurityClearanceById(Integer.parseInt(clearanceId)));
        
        try {
            agent.setActivationDate(LocalDate.parse(activationDate));
        } catch (DateTimeParseException e) {
            agent.setActivationDate(null);
        }
        
        agent.setActive(isActive);
        agent.setPictureUrl(pictureUrl);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        agentEditViolations = validate.validate(agent);
        
        //Go back to Home only if there were no errors with the form
        if(agentEditViolations.isEmpty()) {
            service.addUpdateAgent(agent);
            return "redirect:/";
        }
        
        return "redirect:/viewEditAgent?id=" + identifier;
    }
    
    @GetMapping("clearEditAgentErrors")
    public String clearEditAgentErrors() {
        agentEditViolations.clear();
        return "redirect:/";
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
        
        model.addAttribute("errors", assignmentAddViolations);
        
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
        
        //Try parsing each of the dates
        //If there's an error, make it null
        LocalDate dateStart;
        try {
            dateStart = LocalDate.parse(startDate);            
        } catch (DateTimeParseException e) {
            dateStart = null;
            a.setStartDate(dateStart);
        }
        
        LocalDate dateProjectedEnd;
        try {
            dateProjectedEnd = LocalDate.parse(projectedEndDate);
        } catch (DateTimeParseException e) {
            dateProjectedEnd = null;
            a.setProjectedEndDate(dateProjectedEnd);
        }
        
        LocalDate dateActualEnd;
        try {
            dateActualEnd = LocalDate.parse(actualEndDate);
        } catch (DateTimeParseException e) {
            dateActualEnd = null;
            a.setActualEndDate(dateActualEnd);
        }
        
        //Checking if start date is after either of the end dates
        //If so, make the start date null (meaning there's an error)
        if (dateStart != null && dateProjectedEnd != null) {
            if (dateStart.isAfter(dateProjectedEnd)) {
                dateStart = null;
            }
        }
        
        if (dateStart != null && dateActualEnd != null) {
            if (dateStart.isAfter(dateActualEnd)) {
                dateStart = null;
            }
        }
        
        //Check if Actual is after the Projected end date
        if (dateActualEnd != null && dateProjectedEnd != null ) {
            if (dateActualEnd.isAfter(dateProjectedEnd)) {
                dateProjectedEnd = null;
                dateActualEnd = null;
            }
        }
        
        //Check if start/end dates fall inside an already existing assignment
        //Make sure assignment dates don't overlap
        List<Assignment> aList = service.findAssignmentByAgentIdentifier(agentId);
        if (aList.size() > 0 && dateStart != null && dateProjectedEnd != null) {
            for (Assignment assignment : aList) {
                LocalDate start = assignment.getStartDate();
                LocalDate end = assignment.getProjectedEndDate();
                if (dateStart.isAfter(start) && dateStart.isBefore(end)) {
                    dateStart = null;
                    break;
                }
                if (dateProjectedEnd.isAfter(start) && dateProjectedEnd.isBefore(end)) {
                    dateProjectedEnd = null;
                    break;
                }
                if (dateStart.isBefore(start) && dateProjectedEnd.isAfter(end)) {
                    dateStart = null;
                    dateProjectedEnd = null;
                    break;
                }
                if (dateStart.isAfter(start) && dateProjectedEnd.isBefore(end)) {
                    dateStart = null;
                    dateProjectedEnd = null;
                    break;
                }
            }
        }
        
        a.setStartDate(dateStart);
        a.setProjectedEndDate(dateProjectedEnd);
        a.setActualEndDate(dateActualEnd);
           
        a.setNotes(notes);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        assignmentAddViolations = validate.validate(a);
        
        //Go back to Home only if there were no errors with the form
        if(assignmentAddViolations.isEmpty()) {
            service.addUpdateAssignement(a);
            return "redirect:/viewEditAgent?id=" + agentId;
        }
        
        return "redirect:/addAssignment?id=" + agentId;
    }
    
    @GetMapping("clearAddAssignmentErrors")
    public String clearAddAssignmentErrors() {
        assignmentAddViolations.clear();
        return "redirect:/";
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
        
        model.addAttribute("errors", assignmentEditViolations);
        
        return "viewEditAssignment";
    }
    
    @PostMapping("editAssignment")
    public String editAssignment(HttpServletRequest request) {
        Assignment a = new Assignment();
        String assignmentId = request.getParameter("assignmentId");
        String agentId = request.getParameter("agentId");
        String countryId = request.getParameter("countryId");
        String startDate = request.getParameter("startDate");
        String projectedEndDate = request.getParameter("projectedEndDate");
        String actualEndDate = request.getParameter("actualEndDate");
        String notes = request.getParameter("notes");
        
        a.setAgent(service.findAgentById(agentId));
        a.setCountry(service.findCountryByCode(countryId));
        
        //Try parsing each of the dates
        //If there's an error, make it null
        LocalDate dateStart;
        try {
            dateStart = LocalDate.parse(startDate);            
        } catch (DateTimeParseException e) {
            dateStart = null;
            a.setStartDate(dateStart);
        }
        
        LocalDate dateProjectedEnd;
        try {
            dateProjectedEnd = LocalDate.parse(projectedEndDate);
        } catch (DateTimeParseException e) {
            dateProjectedEnd = null;
            a.setProjectedEndDate(dateProjectedEnd);
        }
        
        LocalDate dateActualEnd;
        try {
            dateActualEnd = LocalDate.parse(actualEndDate);
        } catch (DateTimeParseException e) {
            dateActualEnd = null;
            a.setActualEndDate(dateActualEnd);
        }
        
        //Checking if start date is after either of the end dates
        //If so, make the start date null (meaning there's an error)
        if (dateStart != null && dateProjectedEnd != null) {
            if (dateStart.isAfter(dateProjectedEnd)) {
                dateStart = null;
            }
        }
        
        if (dateStart != null && dateActualEnd != null) {
            if (dateStart.isAfter(dateActualEnd)) {
                dateStart = null;
            }
        }
        
        //Check if Actual is after the Projected end date
        if (dateActualEnd != null && dateProjectedEnd != null ) {
            if (dateActualEnd.isBefore(dateProjectedEnd)) {
                dateProjectedEnd = null;
                dateActualEnd = null;
            }
        }
        
        //Check if start/end dates fall inside an already existing assignment
        //Make sure assignment dates don't overlap
        List<Assignment> aList = service.findAssignmentByAgentIdentifier(agentId);
        if (aList.size() > 0 && dateStart != null && dateProjectedEnd != null) {
            for (Assignment assignment : aList) {
                LocalDate start = assignment.getStartDate();
                LocalDate end = assignment.getProjectedEndDate();
                if (dateStart.isAfter(start) && dateStart.isBefore(end)) {
                    dateStart = null;
                    break;
                }
                if (dateProjectedEnd.isAfter(start) && dateProjectedEnd.isBefore(end)) {
                    dateProjectedEnd = null;
                    break;
                }
                if (dateStart.isBefore(start) && dateProjectedEnd.isAfter(end)) {
                    dateStart = null;
                    dateProjectedEnd = null;
                    break;
                }
                if (dateStart.isAfter(start) && dateProjectedEnd.isBefore(end)) {
                    dateStart = null;
                    dateProjectedEnd = null;
                    break;
                }
            }
        }
        
        a.setStartDate(dateStart);
        a.setProjectedEndDate(dateProjectedEnd);
        a.setActualEndDate(dateActualEnd);
           
        a.setNotes(notes);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        assignmentEditViolations = validate.validate(a);
        
        //Go back to Home only if there were no errors with the form
        if(assignmentEditViolations.isEmpty()) {
            service.addUpdateAssignement(a);
            return "redirect:/viewEditAgent?id=" + agentId;
        }
        
        return "redirect:/viewEditAssignment?id=" + assignmentId;
    }
    
    @GetMapping("clearEditAssignmentErrors")
    public String clearEditAssignmentErrors(int id) {
        assignmentEditViolations.clear();
        
        Assignment a = service.findAssignmentById(id);
        Agent agent = a.getAgent();
        String agentId = agent.getIdentifier();
        return "redirect:/viewEditAgent?id=" + agentId;
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
