package corbos.fieldagent.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assignmentId;   
    
    @NotNull(message = "Invalid Start Date. Value required. Must be before Projected and Actual End dates. "
            + "Agent's Assignment dates must not overlap.")
    private LocalDate startDate;
    
    @NotNull(message = "Invalid Projected End Date. Value required. Must be after Start Date. Agent's Assignment dates must"
            + "not overlap.")
    private LocalDate projectedEndDate;
    
    private LocalDate actualEndDate;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "country_code")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "identifier")
    private Agent agent;

}
