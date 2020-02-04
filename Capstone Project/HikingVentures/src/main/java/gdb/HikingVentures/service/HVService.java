package gdb.HikingVentures.service;

import gdb.HikingVentures.data.EquipmentRepository;
import gdb.HikingVentures.data.LocationRepository;
import gdb.HikingVentures.data.RouteTypeRepository;
import gdb.HikingVentures.data.TrailRepository;
import gdb.HikingVentures.data.TravelerRepository;
import gdb.HikingVentures.data.TripRepository;
import gdb.HikingVentures.entities.Equipment;
import gdb.HikingVentures.entities.Location;
import gdb.HikingVentures.entities.RouteType;
import gdb.HikingVentures.entities.Trail;
import gdb.HikingVentures.entities.Traveler;
import gdb.HikingVentures.entities.Trip;
import java.util.List;
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
    private final TrailRepository trail;
    private final TravelerRepository traveler;
    private final TripRepository trip;

    public HVService(EquipmentRepository equipment, 
            LocationRepository location, 
            RouteTypeRepository routeType, 
            TrailRepository trail, 
            TravelerRepository traveler, 
            TripRepository trip) {
        this.equipment = equipment;
        this.location = location;
        this.routeType = routeType;
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
}
