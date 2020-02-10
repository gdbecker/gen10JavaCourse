package gdb.HikingVentures.service;

import gdb.HikingVentures.data.DifficultyRatingRepository;
import gdb.HikingVentures.data.EquipmentRepository;
import gdb.HikingVentures.data.LocationRepository;
import gdb.HikingVentures.data.RouteTypeRepository;
import gdb.HikingVentures.data.TrailRepository;
import gdb.HikingVentures.data.TravelerRepository;
import gdb.HikingVentures.data.TripRepository;
import gdb.HikingVentures.entities.DifficultyRating;
import gdb.HikingVentures.entities.Equipment;
import gdb.HikingVentures.entities.Location;
import gdb.HikingVentures.entities.RouteType;
import gdb.HikingVentures.entities.Trail;
import gdb.HikingVentures.entities.Traveler;
import gdb.HikingVentures.entities.Trip;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Capstone Project
 * @author garrettbecker
 */

@Service
public class HVService {
    
    private final EquipmentRepository equipment;
    private final LocationRepository location;
    private final RouteTypeRepository routeType;
    private final DifficultyRatingRepository difficultyRating;
    private final TrailRepository trail;
    private final TravelerRepository traveler;
    private final TripRepository trip;

    public HVService(EquipmentRepository equipment, 
            LocationRepository location, 
            RouteTypeRepository routeType,
            DifficultyRatingRepository difficultyRating,
            TrailRepository trail, 
            TravelerRepository traveler, 
            TripRepository trip) {
        this.equipment = equipment;
        this.location = location;
        this.routeType = routeType;
        this.difficultyRating = difficultyRating;
        this.trail = trail;
        this.traveler = traveler;
        this.trip = trip;
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
        Trip t = trip.findById(id).orElse(null);
        return t.getEquipment();
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
    
    //Methods for DifficultyRating
    public List<DifficultyRating> findAllDifficultyRatings() {
        return difficultyRating.findAll();
    }
    
    public DifficultyRating findDifficultyRatingByID(int id) {
        return difficultyRating.findById(id).orElse(null);
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
        Trip t = trip.findById(id).orElse(null);
        return t.getTrails();
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
        Trip t = trip.findById(id).orElse(null);
        return t.getTravelers();
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
}
