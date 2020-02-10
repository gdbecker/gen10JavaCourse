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
    int trailId;
    
    @ManyToOne
    @JoinColumn(name = "location_id")
    Location location;
    //String locationName = location.parkName;
    
    String trailName;
    
    @ManyToOne
    @JoinColumn(name = "difficulty_rating_id")
    DifficultyRating difficultyRating;
    
    @ManyToOne
    @JoinColumn(name = "route_type_id")
    RouteType routeType;
    
    double distance;
    double elevationGain;
    String mapLink;
    String photoLink;
    
    @ManyToMany
    @JoinTable(name = "Trip_Trail",
    joinColumns = {@JoinColumn(name = "trail_id")},
    inverseJoinColumns = {@JoinColumn(name = "trip_id")})
    List<Trip> trips;
}
