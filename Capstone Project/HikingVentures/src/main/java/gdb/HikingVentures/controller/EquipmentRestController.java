package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.Equipment;
import gdb.HikingVentures.entities.Location;
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
 * -getAllEquipment()
 * Used for loading all Equipment from the database into equipmentHome.html
 * 
 * -getEquipment(@RequestParam int id)
 * Used for grabbing a specific Equipment object to load
 */

@RestController
public class EquipmentRestController {
    @Autowired
    HVService service;
    
    @GetMapping("/getAllEquipment")
    public List<Equipment> getAllEquipment() {
        return service.findAllEquipment();
    }
    
    @GetMapping("/getEquipment")
    public Equipment getEquipment(@RequestParam int id) {
        return service.findEquipmentByID(id);
    }
}
