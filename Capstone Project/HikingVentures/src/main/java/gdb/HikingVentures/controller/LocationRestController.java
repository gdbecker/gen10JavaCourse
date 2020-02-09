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
 * For grabbing data for Location
 */

@RestController
public class LocationRestController {
    @Autowired
    HVService service;
    
    @Autowired
    LocationController locationController;
    
    //Get all locations
    //Use to load into locationsHome.html
    @GetMapping("/getAllLocations")
    public List<Location> getAllLocations() {
        return service.findAllLocations();
    }
    
    @GetMapping("/getLocation")
    public Location getLocation(@RequestParam int id) {
        return service.findLocationByID(id);
    }
}
