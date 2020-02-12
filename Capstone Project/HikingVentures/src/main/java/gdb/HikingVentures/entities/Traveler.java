package gdb.HikingVentures.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.List;
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
public class Traveler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int travelerId;
    
    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    String zip;
    LocalDate birthDate;
    String photoLink;
    String photoFilePath;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Trip_Traveler",
    joinColumns = {@JoinColumn(name = "traveler_id")},
    inverseJoinColumns = {@JoinColumn(name = "trip_id")})
    //@JsonIgnore
    List<Trip> trips;
}
