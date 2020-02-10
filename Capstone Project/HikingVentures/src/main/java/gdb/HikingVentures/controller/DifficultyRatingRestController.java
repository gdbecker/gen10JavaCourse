package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.DifficultyRating;
import gdb.HikingVentures.service.HVService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capstone Project
 * @author garrettbecker
 * For grabbing data for DifficultyRating
 */

@RestController
public class DifficultyRatingRestController {
    @Autowired
    HVService service;
    
    //Get all locations
    //Use to load into locationsHome.html
    @GetMapping("/getAllDifficultyRatings")
    public List<DifficultyRating> getAllDifficultyRatings() {
        return service.findAllDifficultyRatings();
    }
}
