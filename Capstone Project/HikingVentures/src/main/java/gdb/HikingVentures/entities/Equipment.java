package gdb.HikingVentures.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;

/**
 * Capstone Project
 * @author garrettbecker
 */

@Entity
@Data
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int equipmentId;
    
    String name;
    String description;
    String photoLink;
    
    @ManyToMany
    @JoinTable(name = "Trip_Equipment",
    joinColumns = {@JoinColumn(name = "equipment_id")},
    inverseJoinColumns = {@JoinColumn(name = "trip_id")})
    List<Trip> trips;
}
