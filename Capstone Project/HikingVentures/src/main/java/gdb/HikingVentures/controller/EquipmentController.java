package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.Equipment;
import gdb.HikingVentures.entities.Trip;
import gdb.HikingVentures.service.HVService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String openEquipmentAdd(Model model) {
        //Add the different Trip options to the page for the user to select from
        List<Trip> allTrips = service.findAllTrips();
        model.addAttribute("trips", allTrips);
        
        return "/equipmentAdd";
    }
    
    //Actually add a new equipment to the db
    @PostMapping("/addNewEquipment")
    public String addNewEquipment(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException{
        Equipment newE = new Equipment();
        String name = request.getParameter("itemNameBox");
        String description = request.getParameter("descriptionBox");
        
        //Trips - get as String array, then find the actual Trip objects and
        //create a List of Trip
        String[] trips = request.getParameterValues("tripsBox");
        List<Trip> allTrips = service.findAllTrips();
        List<Trip> tripsForEquipment = new ArrayList<>();
        if (!trips.equals(null)) {
            for (Trip t : allTrips) {
                for (String s : trips) {
                    if (t.getTripId() == Integer.parseInt(s)) {
                        tripsForEquipment.add(t);
                    }
                }
            }
        }
        
        //Image URL
        String urlFromForm = request.getParameter("filePath");
        URL url = new URL(urlFromForm);
        String fileName = name + ".jpg";
        
        //Remove spaces from the fileName and replace with '-'
        fileName = fileName.replace(' ', '-');
        
        newE.setName(name);
        newE.setDescription(description);
        newE.setTrips(tripsForEquipment);
        newE.setPhotoLink(urlFromForm);
        newE.setPhotoFilePath(fileName);
        
        service.addUpdateEquipment(newE);
        
        //Save the image from url as a file in directory
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream("src/main/resources/static/img/" + fileName);
        
        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        
        //Go back to equipmentHome
        return "redirect:/equipmentHome";
    }
    
    //View specific details for an equipment item
    @GetMapping("/equipmentViewDetails")
    public String openEquipmentViewDetails(@RequestParam Integer id, Model model) {
        Equipment e = service.findEquipmentByID(id);
        model.addAttribute("equipment", e);
        
        //Only get trips that contain the item
        List<Trip> tripsWithItem = e.getTrips();
        model.addAttribute("trips", tripsWithItem);
        
        return "equipmentViewDetails";
    }
    
    //Open page for editing a specific equipment item
    @GetMapping("/equipmentEdit")
    public String openEquipmentEditDetails(@RequestParam Integer id, Model model) {
        Equipment e = service.findEquipmentByID(id);
        model.addAttribute("equipment", e);
        
        List<Trip> trips = service.findAllTrips();
        model.addAttribute("trips", trips);
        
        return "equipmentEdit";
    }
    
    //Actually editing an equipment item
    @PostMapping("/editEquipment")
    public String editEquipment(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException{
        Equipment newE = new Equipment();
        String id = request.getParameter("id");
        String name = request.getParameter("itemNameBox");
        String description = request.getParameter("descriptionBox");
        
        //Trips - get as String array, then find the actual Trip objects and
        //create a List of Trip
        String[] trips = request.getParameterValues("tripsBox");
        List<Trip> allTrips = service.findAllTrips();
        List<Trip> tripsForEquipment = new ArrayList<>();
        if (!trips.equals(null)) {
            for (Trip t : allTrips) {
                for (String s : trips) {
                    if (t.getTripId() == Integer.parseInt(s)) {
                        tripsForEquipment.add(t);
                    }
                }
            }
        }
        
        //Image URL
        String urlFromForm = request.getParameter("filePath");
        URL url = new URL(urlFromForm);
        String fileName = name + ".jpg";
        
        //Remove spaces from the fileName and replace with '-'
        fileName = fileName.replace(' ', '-');
        
        newE.setEquipmentId(Integer.parseInt(id));
        newE.setName(name);
        newE.setDescription(description);
        newE.setTrips(tripsForEquipment);
        newE.setPhotoLink(urlFromForm);
        newE.setPhotoFilePath(fileName);
        
        service.addUpdateEquipment(newE);
        
        //Save the image from url as a file in directory
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream("src/main/resources/static/img/" + fileName);
        
        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        
        //Go back to equipmentHome
        return "redirect:/equipmentHome";
    }
    
    //Delete methods
    @GetMapping("/equipmentDelete")
    public String deleteEquipment(@RequestParam Integer id) {
        service.deleteEquipmentByID(id);
        return "redirect:/equipmentHome";
    }
}
