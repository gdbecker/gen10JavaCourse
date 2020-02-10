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
 * For grabbing data for Traveler
 */

@RestController
public class TravelerRestController {
    @Autowired
    HVService service;
    
    //Get all travelers
    //Use to load into travelersHome.html
    @GetMapping("/getAllTravelers")
    public List<Traveler> getAllTravelers() {
        return service.findAllTravelers();
    }
    
    @GetMapping("/getTraveler")
    public Traveler getTraveler(@RequestParam int id) {
        return service.findTravelerByID(id);
    }
}
