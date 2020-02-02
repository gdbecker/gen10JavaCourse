package corbos.fieldagent.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Agent {

    @Id
    @NotBlank(message = "Identifier must not be blank")
    @Size(max = 25, message="Identifier must be fewer than 25 characters")
    private String identifier;
    
    @NotBlank(message = "First Name must not be blank")
    @Size(max = 25, message="First Name must be fewer than 25 characters")
    private String firstName;
    
    @Size(max = 25, message="Middle Name must be fewer than 25 characters")
    private String middleName;
    
    @NotBlank(message = "Last Name must not be blank")
    @Size(max = 25, message="Last Name must be fewer than 25 characters")
    private String lastName;
    
    @Size(max = 255, message="Picture URL must be fewer than 255 characters")
    private String pictureUrl;
    
    private LocalDate birthDate;
    
    @Min(value = 36, message = "Height must be in between 36 and 96 inches.")
    @Max(value = 96, message = "Height must be in between 36 and 96 inches.")
    private int height;
    
    private LocalDate activationDate;
    
    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "security_clearance_id")
    private SecurityClearance securityClearance;

}
