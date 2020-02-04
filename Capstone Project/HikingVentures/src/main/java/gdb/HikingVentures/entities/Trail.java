package gdb.HikingVentures.entities;

import java.util.List;
import javax.persistence.Entity;
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
public class Trail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int trailID;
    
    @ManyToOne
    @JoinColumn(name = "LocationID")
    Location location;
    
    String trailName;
    String difficultyRating;
    
    @ManyToOne
    @JoinColumn(name = "RouteTypeID")
    RouteType routeType;
    
    double distance;
    double elevationGain;
    String mapLink;
    
    @ManyToMany
    @JoinTable(name = "Trip_Trail",
    joinColumns = {@JoinColumn(name = "TrailID")},
    inverseJoinColumns = {@JoinColumn(name = "TripID")})
    List<Trip> trips;
}
