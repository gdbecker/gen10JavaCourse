package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.Trail;
import gdb.HikingVentures.service.HVService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capstone Project
 * @author garrettbecker
 * For grabbing data for Trail
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
}
