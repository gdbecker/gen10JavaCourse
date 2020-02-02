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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Agent {

    @Id
    @NotBlank(message = "Identifier is required.")
    @Size(max = 25, message="Identifier must be fewer than 25 characters")
    private String identifier;
    
    @NotBlank(message = "First Name is required.")
    @Size(max = 25, message="First Name must be fewer than 25 characters")
    private String firstName;
    
    @Size(max = 25, message="Middle Name must be fewer than 25 characters")
    private String middleName;
    
    @NotBlank(message = "Last Name is required.")
    @Size(max = 25, message="Last Name must be fewer than 25 characters")
    private String lastName;
    
    @Size(max = 255, message="Picture URL must be fewer than 255 characters")
    private String pictureUrl;
    
    @NotNull(message = "Invalid Birth Date. Must be between 1900-01-01 and 10 years before today's date.")
    //@Pattern(regexp = "yyyy-MM-dd", message = "Birth Date must follow pattern yyyy-MM-dd")
    private LocalDate birthDate;
    
    @Min(value = 36, message = "Invalid Height: Must be in between 36 and 96 inches.")
    @Max(value = 96, message = "Invalid Height: Must be in between 36 and 96 inches.")
    private int height;
    
    @NotNull(message = "Invalid Activation Date.")
    //@Pattern(regexp = "yyyy-MM-dd", message = "Activation Date must follow pattern yyyy-MM-dd")
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
