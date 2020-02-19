package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.Traveler;
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
 * -getAllTravelers()
 * Used for loading all Traveler from the database into travelersHome.html
 * 
 * -getTraveler(@RequestParam int id)
 * Used for grabbing a specific Traveler object to load
 */

@RestController
public class TravelerRestController {
    @Autowired
    HVService service;
    
    @GetMapping("/getAllTravelers")
    public List<Traveler> getAllTravelers() {
        return service.findAllTravelers();
    }
    
    @GetMapping("/getTraveler")
    public Traveler getTraveler(@RequestParam int id) {
        return service.findTravelerByID(id);
    }
}
