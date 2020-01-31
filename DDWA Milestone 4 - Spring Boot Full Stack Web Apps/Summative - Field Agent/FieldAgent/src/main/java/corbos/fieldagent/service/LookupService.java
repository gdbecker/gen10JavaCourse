package corbos.fieldagent.service;

import corbos.fieldagent.data.AgencyRepository;
import corbos.fieldagent.data.AgentRepository;
import corbos.fieldagent.data.AssignmentRepository;
import corbos.fieldagent.data.CountryRepository;
import corbos.fieldagent.data.SecurityClearanceRepository;
import corbos.fieldagent.entities.Agency;
import corbos.fieldagent.entities.Agent;
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
    
    public Agent findAgentById(int agentId) {
        return agentRepo.findById(agentId).orElse(null);
    }
    
    //Methods for Assignment
    
    //Methods for Agency
    public List<Agency> findAllAgencies() {
        return agencyRepo.findAll();
    }

    public Agency findAgencyById(int agencyId) {
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
