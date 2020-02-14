package gdb.HikingVentures.service;

import gdb.HikingVentures.data.DifficultyRatingRepository;
import gdb.HikingVentures.data.EquipmentRepository;
import gdb.HikingVentures.data.LocationRepository;
import gdb.HikingVentures.data.RouteTypeRepository;
import gdb.HikingVentures.data.TrailRepository;
import gdb.HikingVentures.data.TravelerRepository;
import gdb.HikingVentures.data.TripRepository;
import gdb.HikingVentures.data.UserRepository;
import gdb.HikingVentures.entities.DifficultyRating;
import gdb.HikingVentures.entities.Equipment;
import gdb.HikingVentures.entities.Location;
import gdb.HikingVentures.entities.Role;
import gdb.HikingVentures.entities.RouteType;
import gdb.HikingVentures.entities.Trail;
import gdb.HikingVentures.entities.Traveler;
import gdb.HikingVentures.entities.Trip;
import gdb.HikingVentures.entities.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Capstone Project
 * @author garrettbecker
 */

@Service
public class HVService implements UserDetailsService {
    
    @Autowired
    DifficultyRatingRepository difficultyRating;
    
    @Autowired
    EquipmentRepository equipment;
    
    @Autowired
    LocationRepository location;
    
    @Autowired
    RouteTypeRepository routeType;
    
    @Autowired
    TravelerRepository traveler;
    
    @Autowired
    TrailRepository trail;
    
    @Autowired
    TripRepository trip;
    
    //Security entities/methods
    @Autowired
    UserRepository users;
  
    // *************************** //
    
    //Methods for DifficultyRating
    public List<DifficultyRating> findAllDifficultyRatings() {
        return difficultyRating.findAll();
    }
    
    public DifficultyRating findDifficultyRatingByID(int id) {
        return difficultyRating.findById(id).orElse(null);
    }
    
    //Methods for Equipment
    public List<Equipment> findAllEquipment() {
        return equipment.findAll();
    }
    
    public Equipment findEquipmentByID(int id) {
        return equipment.findById(id).orElse(null);
    }
    
    public Equipment addUpdateEquipment(Equipment e) {
        return equipment.save(e);
    }
    
    public void deleteEquipmentByID(int id) {
        equipment.deleteById(id);
    }
    
    public List<Equipment> findAllEquipmentByTripID(int id) {
        List<Equipment> allEquipment = equipment.findAll();
        List<Equipment> equipmentOnTrip = new ArrayList<Equipment>();
        
        for (Equipment e : allEquipment) {
            List<Trip> trips = e.getTrips();
            
            for (Trip t : trips) {
                if (t.getTripId() == id) {
                    equipmentOnTrip.add(e);
                }
            }
        }
        
        return equipmentOnTrip;
    }
    
    //Methods for Location
    public List<Location> findAllLocations() {
        return location.findAll();
    }
    
    public Location findLocationByID(int id) {
        return location.findById(id).orElse(null);
    }
    
    public Location addUpdateLocation(Location l) {
        return location.save(l);
    }
    
    public void deleteLocationByID(int id) {
        location.deleteById(id);
    }
    
    //Methods for RouteType
    public List<RouteType> findAllRouteTypes() {
        return routeType.findAll();
    }
    
    public RouteType findRouteTypeByID(int id) {
        return routeType.findById(id).orElse(null);
    }
    
    //Methods for Trail
    public List<Trail> findAllTrails() {
        return trail.findAll();
    }
    
    public Trail findTrailByID(int id) {
        return trail.findById(id).orElse(null);
    }
    
    public Trail addUpdateTrail(Trail t) {
        return trail.save(t);
    }
    
    public void deleteTrailByID(int id) {
        trail.deleteById(id);
    }
    
    public List<Trail> findAllTrailsByLocationID(int id) {
        Location l = location.findById(id).orElse(null);
        List<Trail> trails = trail.findAll();
        List<Trail> trailsWithLocation = new ArrayList<Trail>();
        
        for (Trail t : trails) {
            if (t.getLocation().equals(l)) {
                trailsWithLocation.add(t);
            }
        }
        
        return trailsWithLocation;
    }
    
    public List<Trail> findAllTrailsByTripID(int id) {
        List<Trail> allTrails = trail.findAll();
        List<Trail> trailsOnTrip = new ArrayList<Trail>();
        
        for (Trail tr : allTrails) {
            List<Trip> trips = tr.getTrips();
            
            for (Trip t : trips) {
                if (t.getTripId() == id) {
                    trailsOnTrip.add(tr);
                }
            }
        }
        
        return trailsOnTrip;
    }
    
    //Methods for Traveler
    public List<Traveler> findAllTravelers() {
        return traveler.findAll();
    }
    
    public Traveler findTravelerByID(int id) {
        return traveler.findById(id).orElse(null);
    }
    
    public Traveler addUpdateTraveler(Traveler t) {
        return traveler.save(t);
    }
    
    public void deleteTravelerByID(int id) {
        traveler.deleteById(id);
    }
    
    public List<Traveler> findAllTravelersByTripID(int id) {
        List<Traveler> allTravelers = traveler.findAll();
        List<Traveler> travelersOnTrip = new ArrayList<Traveler>();
        
        for (Traveler tr : allTravelers) {
            List<Trip> trips = tr.getTrips();
            
            for (Trip t : trips) {
                if (t.getTripId() == id) {
                    travelersOnTrip.add(tr);
                }
            }
        }
        
        return travelersOnTrip;
    }
    
    //Methods for Trip
    public List<Trip> findAllTrips() {
        return trip.findAll();
    }
    
    public Trip findTripByID(int id) {
        return trip.findById(id).orElse(null);
    }
    
    public Trip addUpdateTrip(Trip t) {
        return trip.save(t);
    }
    
    public void deleteTripByID(int id) {
        trip.deleteById(id);
    }
    
    public List<Trip> findAllTripsByTravelerID(int id) {
        Traveler t = traveler.findById(id).orElse(null);
        return t.getTrips();
    }
    
    public List<Trip> findAllTripsByEquipmentID(int id) {
        Equipment e = equipment.findById(id).orElse(null);
        return e.getTrips();
    }
    
    public List<Trip> findAllTripsByTrailID(int id) {
        Trail t = trail.findById(id).orElse(null);
        return t.getTrips();
    }
    
    public List<Trip> findAllTripsByLocationID(int id) {
        Location l = location.findById(id).orElse(null);
        
        List<Trail> trails = trail.findAll();
        List<Trail> trailsWithLocation = new ArrayList<Trail>();
        
        for (Trail t : trails) {
            if (t.getLocation().equals(l)) {
                trailsWithLocation.add(t);
            }
        }
        
        List<Trip> tripsWithLocation = new ArrayList<Trip>();
        
        for (Trail t : trailsWithLocation) {
            List<Trip> tripsOnTrail = t.getTrips();
            for (Trip x : tripsOnTrail) {
                tripsWithLocation.add(x);
            }
        }
        
        return tripsWithLocation;
    }
    
    //Security Methods
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        //Get the user from the dv that matches the one inputted
        List<User> allUsers = users.findAll();
        User user = new User();
        for (User u : allUsers) {
            if (u.getUsername().equals(username)) {
                user = u;
            }
        }
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
