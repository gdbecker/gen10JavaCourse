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
 * For grabbing data for Equipment
 */

@RestController
public class EquipmentRestController {
    @Autowired
    HVService service;
    
    //Get all equipment
    //Use to load into equipmentHome.html
    @GetMapping("/getAllEquipment")
    public List<Equipment> getAllEquipment() {
        return service.findAllEquipment();
    }
    
    @GetMapping("/getEquipment")
    public Equipment getEquipment(@RequestParam int id) {
        return service.findEquipmentByID(id);
    }
}
