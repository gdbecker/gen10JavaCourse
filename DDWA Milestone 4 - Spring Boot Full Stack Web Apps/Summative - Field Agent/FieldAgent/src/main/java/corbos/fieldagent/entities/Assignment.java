package corbos.fieldagent.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assignmentId;   
    
    @NotBlank(message = "Start Date must not be blank")
    private LocalDate startDate;
    
    @NotBlank(message = "Projected End Date must not be blank")
    private LocalDate projectedEndDate;
    
    private LocalDate actualEndDate;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "country_code")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "identifier")
    @NotBlank(message = "Agent Name must not be blank")
    private Agent agent;

}
