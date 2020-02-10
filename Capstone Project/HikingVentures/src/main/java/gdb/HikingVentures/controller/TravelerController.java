package gdb.HikingVentures.controller;

import gdb.HikingVentures.service.HVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capstone Project
 * @author garrettbecker
 * For opening Traveler web pages
 */

@Controller
public class TravelerController {
    @Autowired
    HVService service;
    
    //Open travelersHome.html
    @RequestMapping("/travelersHome")
    public String openTravelersHome() {
        return "/travelersHome";
    }
    
    //Open travelersAdd.html
    @RequestMapping("/travelersAdd")
    public String openTravelersAdd() {
        return "/travelersAdd";
    }
}
