package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.Location;
import gdb.HikingVentures.service.HVService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capstone Project
 * @author garrettbecker
 * For opening Location web pages
 */

@Controller
public class LocationController {
    @Autowired
    HVService service;
    
    //Open locationsHome.html
    @RequestMapping("/locationsHome")
    public String openLocationsHome() {
        return "/locationsHome.html";
    }
    
    //Open locationsAdd.html
    @RequestMapping("/locationsAdd")
    public String openLocationsAdd() {
        return "/locationsAdd.html";
    }
    
    @GetMapping("locationsViewDetails")
    public String openLocationsViewDetails(int id, Model model) {
        Location l = service.findLocationByID(id);
        model.addAttribute("location", l);
        
        return "locationsViewDetails.html";
    }
}
