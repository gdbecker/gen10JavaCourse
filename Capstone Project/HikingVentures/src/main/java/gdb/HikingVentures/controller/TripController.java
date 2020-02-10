package gdb.HikingVentures.controller;

import gdb.HikingVentures.service.HVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capstone Project
 * @author garrettbecker
 * For opening Trip web pages
 */

@Controller
public class TripController {
    @Autowired
    HVService service;
    
    //Open tripsHome.html
    @RequestMapping("/tripsHome")
    public String openTripsHome() {
        return "/tripsHome";
    }
    
    //Open tripsAdd.html
    @RequestMapping("/tripsAdd")
    public String openTripsAdd() {
        return "/tripsAdd";
    }
}
