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
 * -getAllTrails()
 * Used for loading all Trail from the database into trailsHome.html
 * 
 * -getTrail(@RequestParam int id)
 * Used for grabbing a specific Trail object to load
 * 
 * -getTrailsByTripId(@RequestParam int id)
 * Used for getting a list of Trail objects that are associated with a specific
 * Trip id. This is used for showing the maps of trails while viewing a trip's
 * details.
 */

@RestController
public class TrailRestController {
    @Autowired
    HVService service;
    
    //Get all trails
    //Use to load into trailsHome.html
    @GetMapping("/getAllTrails")
    public List<Trail> getAllTrails() {
        return service.findAllTrails();
    }
    
    @GetMapping("/getTrail")
    public Trail getTrail(@RequestParam int id) {
        return service.findTrailByID(id);
    }
    
    @GetMapping("/getTrailsByTripId")
    public List<Trail> getTrailsByTripId(@RequestParam int id) {
        Trip t = service.findTripByID(id);
        return t.getTrails();
    }
}
