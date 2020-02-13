package gdb.HikingVentures.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    int tripId;
    
    String tripName;
    double tripCostPerTraveler;
    LocalDate startDate;
    LocalDate endDate;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Trip_Traveler",
    joinColumns = {@JoinColumn(name = "trip_id")},
    inverseJoinColumns = {@JoinColumn(name = "traveler_id")})
    @JsonIgnore
    List<Traveler> travelers;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Trip_Equipment",
    joinColumns = {@JoinColumn(name = "trip_id")},
    inverseJoinColumns = {@JoinColumn(name = "equipment_id")})
    @JsonIgnore
    List<Equipment> equipment;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Trip_Trail",
    joinColumns = {@JoinColumn(name = "trip_id")},
    inverseJoinColumns = {@JoinColumn(name = "trail_id")})
    @JsonIgnore
    List<Trail> trails; 

}
