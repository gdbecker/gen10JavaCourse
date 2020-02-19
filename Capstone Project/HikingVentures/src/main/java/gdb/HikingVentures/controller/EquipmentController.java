package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.Equipment;
import gdb.HikingVentures.entities.Trip;
import gdb.HikingVentures.service.HVService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capstone Project
 * @author garrettbecker
 * 
 * This Controller focuses on managing all pages relating to the Equipment object
 * 
 * Methods:
 * -openEquipmentHome()
 * Opens equipmentHome.html
 * 
 * -openEquipmentAdd(Model model)
 * Opens equipmentAdd.html. Loads in all the Trips from the db.
 * 
 * -addNewEquipment(HttpServletRequest request, HttpServletResponse response, Model model)
 * Actually adds a new Equipment object to the db
 * 
 * -openEquipmentViewDetails(@RequestParam Integer id, Model model)
 * View specific details for an Equipment object
 * 
 * -openEquipmentEditDetails(@RequestParam Integer id, Model model)
 * Open page for editing a specific Equipment object
 * 
 * -editEquipment(HttpServletRequest request, HttpServletResponse response, Model model)
 * Actually editing an Equipment object
 * 
 * -deleteEquipment(@RequestParam Integer id)
 * Deleting an Equipment object
 * 
 */

@Controller
public class EquipmentController {
    @Autowired
    HVService service;
    
    @RequestMapping("/equipmentHome")
    public String openEquipmentHome() {
        return "/equipmentHome";
    }
    
    @RequestMapping("/equipmentAdd")
    public String openEquipmentAdd(Model model) {
        //Add the different Trip options to the page for the user to select from
        List<Trip> allTrips = service.findAllTrips();
        model.addAttribute("trips", allTrips);
        
        return "/equipmentAdd";
    }
    
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
        if (trips != null) {
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
        URL url = null;
        if (!urlFromForm.equals("")) {
            url = new URL(urlFromForm);
        }
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
        if (!urlFromForm.equals("")) {
            try {
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream("src/main/resources/static/img/" + fileName);
        
                byte[] b = new byte[2048];
                int length;

                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
            } catch (MalformedURLException e) {
                
            } catch (IOException ex) {
                
            }
        }
        
        //Go back to equipmentHome
        return "redirect:/equipmentHome";
    }
    
    @GetMapping("/equipmentViewDetails")
    public String openEquipmentViewDetails(@RequestParam Integer id, Model model) {
        Equipment e = service.findEquipmentByID(id);
        model.addAttribute("equipment", e);
        
        //Only get trips that contain the item
        List<Trip> tripsWithItem = e.getTrips();
        model.addAttribute("trips", tripsWithItem);
        
        return "equipmentViewDetails";
    }
    
    @GetMapping("/equipmentEdit")
    public String openEquipmentEditDetails(@RequestParam Integer id, Model model) {
        Equipment e = service.findEquipmentByID(id);
        model.addAttribute("equipment", e);
        
        List<Trip> trips = service.findAllTrips();
        model.addAttribute("trips", trips);
        
        return "equipmentEdit";
    }
    
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
        if (trips != null) {
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
        URL url = null;
        if (!urlFromForm.equals("")) {
            url = new URL(urlFromForm);
        }
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
        if (!urlFromForm.equals("")) {
            try {
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream("src/main/resources/static/img/" + fileName);
        
                byte[] b = new byte[2048];
                int length;

                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
            } catch (MalformedURLException e) {
                
            } catch (IOException ex) {
                
            }
        }
        
        //Go back to equipmentHome
        return "redirect:/equipmentHome";
    }
    
    @GetMapping("/equipmentDelete")
    public String deleteEquipment(@RequestParam Integer id) {
        service.deleteEquipmentByID(id);
        return "redirect:/equipmentHome";
    }
}
