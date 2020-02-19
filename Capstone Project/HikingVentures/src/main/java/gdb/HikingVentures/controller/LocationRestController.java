package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.Location;
import gdb.HikingVentures.service.HVService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * -getAllLocations()
 * Used for loading all Location from the database into locationsHome.html
 * 
 * -getLocation(@RequestParam int id)
 * Used for grabbing a specific Location object to load
 */

@RestController
public class LocationRestController {
    @Autowired
    HVService service;
    
    @GetMapping("/getAllLocations")
    public List<Location> getAllLocations() {
        return service.findAllLocations();
    }
    
    @GetMapping("/getLocation")
    public Location getLocation(@RequestParam int id) {
        return service.findLocationByID(id);
    }
}
