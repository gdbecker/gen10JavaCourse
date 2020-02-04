package gdb.HikingVentures.entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;

/**
 * Capstone Project
 * @author garrettbecker
 */

@Entity
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int tripID;
    
    String tripName;
    double tripCostPerTraveler;
    LocalDate startDate;
    LocalDate endDate;
    
    @ManyToMany(mappedBy = "trips")
    List<Traveler> travelers;
    
    @ManyToMany(mappedBy = "trips")
    List<Equipment> equipment;
    
    @ManyToMany(mappedBy = "trips")
    List<Trail> trails;
}
