package corbos.fieldagent.service;

import corbos.fieldagent.data.AgencyRepository;
import corbos.fieldagent.data.AgentRepository;
import corbos.fieldagent.data.AssignmentRepository;
import corbos.fieldagent.data.CountryRepository;
import corbos.fieldagent.data.SecurityClearanceRepository;
import corbos.fieldagent.entities.Agency;
import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.entities.Assignment;
import corbos.fieldagent.entities.Country;
import corbos.fieldagent.entities.SecurityClearance;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LookupService {

    private final AgentRepository agentRepo;
    private final AssignmentRepository assignmentRepo;
    private final AgencyRepository agencyRepo;
    private final CountryRepository countryRepo;
    private final SecurityClearanceRepository securityRepo;

    public LookupService(AgentRepository agentRepo, 
            AssignmentRepository assignmentRepo, 
            AgencyRepository agencyRepo,
            CountryRepository countryRepo,
            SecurityClearanceRepository securityRepo) {
        this.agentRepo = agentRepo;
        this.assignmentRepo = assignmentRepo;
        this.agencyRepo = agencyRepo;
        this.countryRepo = countryRepo;
        this.securityRepo = securityRepo;
    }

    //Methods for Agent
    public List<Agent> findAllAgents() {
        return agentRepo.findAll();
    }
    
    public Agent findAgentById(String agentId) {
        return agentRepo.findById(agentId).orElse(null);
    }
    
    public Agent addUpdateAgent(Agent a) {
        return agentRepo.save(a);
    }
    
    public void deleteAgentById(String agentId) {
        agentRepo.deleteById(agentId);
    }
    
    //Methods for Assignment
    public List<Assignment> findAllAssignments() {
        return assignmentRepo.findAll();
    }
    
    public List<Assignment> findAssignmentByAgentIdentifier(String agentIdentifier) {
        return assignmentRepo.findByAgentIdentifier(agentIdentifier);
    }
    
    public Assignment findAssignmentById(int id) {
        return assignmentRepo.findById(id).orElse(null);
    }
    
    public Assignment addUpdateAssignement(Assignment a) {
        return assignmentRepo.save(a);
    }
    
    public void deleteAssignment(Assignment a) {
        assignmentRepo.delete(a);
    }
    
    //Methods for Agency
    public List<Agency> findAllAgencies() {
        return agencyRepo.findAll();
    }

    public Agency findAgencyByAgencyId(int agencyId) {
        return agencyRepo.findById(agencyId)
                .orElse(null);
    }

    //Methods for Country
    public List<Country> findAllCountries() {
        return countryRepo.findAll();
    }

    public Country findCountryByCode(String countryCode) {
        return countryRepo.findById(countryCode)
                .orElse(null);
    }

    //Methods for Security Clearance
    public List<SecurityClearance> findAllSecurityClearances() {
        return securityRepo.findAll();
    }

    public SecurityClearance findSecurityClearanceById(int securityClearanceId) {
        return securityRepo.findById(securityClearanceId)
                .orElse(null);
    }
}
