package gdb.HikingVentures.controller;

import gdb.HikingVentures.service.HVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capstone Project
 * @author garrettbecker
 * For opening Equipment web pages
 */

@Controller
public class EquipmentController {
    @Autowired
    HVService service;
    
    //Open equipmentHome.html
    @RequestMapping("/equipmentHome")
    public String openEquipmentHome() {
        return "/equipmentHome";
    }
    
    //Open equipmentAdd.html
    @RequestMapping("/equipmentAdd")
    public String openEquipmentAdd() {
        return "/equipmentAdd";
    }
}
