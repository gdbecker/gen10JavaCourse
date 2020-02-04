package gdb.HikingVentures.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 * Capstone Project
 * @author garrettbecker
 */

@Entity
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int locationID;
    
    String parkName;
    String nearbyCity;
    String state;
}
