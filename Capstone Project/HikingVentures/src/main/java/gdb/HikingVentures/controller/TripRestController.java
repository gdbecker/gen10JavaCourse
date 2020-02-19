package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.Trail;
import gdb.HikingVentures.entities.Trip;
import gdb.HikingVentures.service.HVService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capstone Project
 * @author garrettbecker
 * 
 * This controller is a specifically a REST Controller built just for grabbing
 * some specific data. These methods are called on the client side through
 * JavaScript.
 * 
 * Methods:
 * -getAllTrips()
 * Used for loading all Trip from the database into tripsHome.html
 * 
 * -getTrip(@RequestParam int id)
 * Used for grabbing a specific Trip object to load
 * 
 * -getTrailsForTrip(@RequestParam int id)
 * Used for grabbing all the Trail objects associated with a Trip by taking in a
 * Trip id.
 */

@RestController
public class TripRestController {
    @Autowired
    HVService service;
    
    @GetMapping("/getAllTrips")
    public List<Trip> getAllTrips() {
        return service.findAllTrips();
    }
    
    @GetMapping("/getTrip")
    public Trip getTrip(@RequestParam int id) {
        return service.findTripByID(id);
    }
    
    @GetMapping("/getTrailsForTrip")
    public List<Trail> getTrailsForTrip(@RequestParam int id) {
        return service.findAllTrailsByTripID(id);
    }
}
