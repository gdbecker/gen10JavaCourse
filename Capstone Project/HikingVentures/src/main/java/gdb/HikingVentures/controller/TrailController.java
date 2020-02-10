package gdb.HikingVentures.controller;

import gdb.HikingVentures.service.HVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capstone Project
 * @author garrettbecker
 * For opening Trail web pages
 */

@Controller
public class TrailController {
    @Autowired
    HVService service;
    
    //Open trailsHome.html
    @RequestMapping("/trailsHome")
    public String openTrailsHome() {
        return "/trailsHome";
    }
    
    //Open trailsAdd.html
    @RequestMapping("/trailsAdd")
    public String openTrailsAdd() {
        return "/trailsAdd";
    }
}
