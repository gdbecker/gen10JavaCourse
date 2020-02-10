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
 * For grabbing data for Trip
 */

@RestController
public class TripRestController {
    @Autowired
    HVService service;
    
    //Get all trip
    //Use to load into tripHome.html
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
