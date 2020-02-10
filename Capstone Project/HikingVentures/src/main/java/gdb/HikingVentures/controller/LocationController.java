package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.Location;
import gdb.HikingVentures.entities.Trail;
import gdb.HikingVentures.entities.Trip;
import gdb.HikingVentures.service.HVService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        return "/locationsHome";
    }
    
    //Open locationsAdd.html
    @RequestMapping("/locationsAdd")
    public String openLocationsAdd() {
        return "/locationsAdd";
    }
    
    //Open locationsViewDetails.html
    @GetMapping("/locationsViewDetails")
    public String openLocationsViewDetails(@RequestParam Integer id, Model model) {
        Location l = service.findLocationByID(id);
        model.addAttribute("location", l);
        
        List<Trail> trails = service.findAllTrailsByLocationID(id);
        model.addAttribute("trails", trails);
        
        List<Trip> trips = service.findAllTripsByLocationID(id);
        model.addAttribute("trips", trips);
        
        return "locationsViewDetails";
    }
    
    //Open locationsEdit.html
    @GetMapping("/locationsEdit")
    public String openLocationsEditDetails(@RequestParam Integer id, Model model) {
        Location l = service.findLocationByID(id);
        model.addAttribute("location", l);
        return "locationsEdit";
    }
    
    //Open about.html
    @RequestMapping("/about")
    public String openAbout() {
        return "/about";
    }
    
    @GetMapping("/locationsDelete")
    public String deleteLocation(@RequestParam Integer id) {
        service.deleteLocationByID(id);
        return "redirect:/locationsHome";
    }
    
    @GetMapping("/locationsDeleteFromView")
    public String deleteLocationFromView(@RequestParam Integer id) {
        //Delete trails and trips here
        
        service.deleteLocationByID(id);
        return "redirect:/locationsHome";
    }
}
