package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.RouteType;
import gdb.HikingVentures.service.HVService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capstone Project
 * @author garrettbecker
 * For grabbing data for RouteType
 */

@RestController
public class RouteTypeRestController {
    @Autowired
    HVService service;
    
    //Get all RouteType
    @GetMapping("/getAllRouteTypes")
    public List<RouteType> getAllRouteTypes() {
        return service.findAllRouteTypes();
    }
}
