package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.Traveler;
import gdb.HikingVentures.entities.Trip;
import gdb.HikingVentures.service.HVService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
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

/**
 * Capstone Project
 * @author garrettbecker
 * 
 * This Controller focuses on managing all pages relating to the Traveler object
 * 
 * Methods:
 * -openTravelersHome()
 * Open travelersHome.html
 * 
 * -openTravelersAdd(Model model)
 * Open travelersAdd.html
 * 
 * -addNewEquipment(HttpServletRequest request, HttpServletResponse response, Model model)
 * Actually add a new traveler to the db
 * 
 * -openTravelerViewDetails(@RequestParam Integer id, Model model)
 * View specific details for a traveler
 * 
 * -openTravelersEditDetails(@RequestParam Integer id, Model model)
 * Open page for editing a specific traveler
 * 
 * -editTraveler(HttpServletRequest request, HttpServletResponse response, Model model)
 * Actually editing a traveler
 * 
 * -deleteTraveler(@RequestParam Integer id)
 * Deleting a Traveler from the db
 */

@Controller
public class TravelerController {
    @Autowired
    HVService service;
    
    @RequestMapping("/travelersHome")
    public String openTravelersHome() {
        return "/travelersHome";
    }
    
    @RequestMapping("/travelersAdd")
    public String openTravelersAdd(Model model) {
        //Add the different Trip options to the page for the user to select from
        List<Trip> allTrips = service.findAllTrips();
        model.addAttribute("trips", allTrips);
        
        return "/travelersAdd";
    }
    
    @PostMapping("/addNewTraveler")
    public String addNewEquipment(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException{
        Traveler newT = new Traveler();
        String firstName = request.getParameter("firstNameBox");
        String lastName = request.getParameter("lastNameBox");
        String address = request.getParameter("addressBox");
        String city = request.getParameter("cityBox");
        String state = request.getParameter("stateBox");
        String zip = request.getParameter("zipCodeBox");
        
        //BirthDate - get as String, convert to LocalDate
        String birth = request.getParameter("birthDateBox");
        LocalDate birthDate = null;
        if (!birth.equals("")) {
            birthDate = LocalDate.parse(birth);
        }
        
        //Trips - get as String array, then find the actual Trip objects and
        //create a List of Trip
        String[] trips = request.getParameterValues("tripsBox");
        List<Trip> allTrips = service.findAllTrips();
        List<Trip> tripsForTraveler = new ArrayList<>();
        if (trips != null) {
            for (Trip t : allTrips) {
                for (String s : trips) {
                    if (t.getTripId() == Integer.parseInt(s)) {
                        tripsForTraveler.add(t);
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
        String fileName = firstName + " " + lastName + ".jpg";
        
        //Remove spaces from the fileName and replace with '-'
        fileName = fileName.replace(' ', '-');
        
        //Add everything to the object
        newT.setFirstName(firstName);
        newT.setLastName(lastName);
        newT.setAddress(address);
        newT.setCity(city);
        newT.setState(state);
        newT.setZip(zip);
        newT.setBirthDate(birthDate);
        newT.setTrips(tripsForTraveler);
        newT.setPhotoLink(urlFromForm);
        newT.setPhotoFilePath(fileName);
        
        service.addUpdateTraveler(newT);
        
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
        
        //Go back to travelersHome
        return "redirect:/travelersHome";
    }
    
    @GetMapping("/travelersViewDetails")
    public String openTravelerViewDetails(@RequestParam Integer id, Model model) {
        Traveler tr = service.findTravelerByID(id);
        model.addAttribute("traveler", tr);
        
        //Only get trips that contain the traveler
        List<Trip> allTrips = service.findAllTrips();
        List<Trip> tripsWithTraveler = new ArrayList<>();
        for (Trip t : allTrips) {
            List<Traveler> travelers = t.getTravelers();
            for (Traveler trav : travelers) {
                if (trav.getTravelerId() == id) {
                    tripsWithTraveler.add(t);
                }
            }
        }
        model.addAttribute("trips", tripsWithTraveler);
        
        return "travelersViewDetails";
    }
    
    @GetMapping("/travelersEdit")
    public String openTravelersEditDetails(@RequestParam Integer id, Model model) {
        Traveler t = service.findTravelerByID(id);
        model.addAttribute("traveler", t);
       
        List<Trip> trips = service.findAllTrips();
        model.addAttribute("trips", trips);
        
        return "travelersEdit";
    }
    
    @PostMapping("/editTraveler")
    public String editTraveler(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException{
        Traveler newT = new Traveler();
        String id = request.getParameter("id");
        String firstName = request.getParameter("firstNameBox");
        String lastName = request.getParameter("lastNameBox");
        String address = request.getParameter("addressBox");
        String city = request.getParameter("cityBox");
        String state = request.getParameter("stateBox");
        String zip = request.getParameter("zipCodeBox");
        
        //BirthDate - get as String, convert to LocalDate
        String birth = request.getParameter("birthDateBox");
        LocalDate birthDate = null;
        if (!birth.equals("")) {
            birthDate = LocalDate.parse(birth);
        }
        
        //Trips - get as String array, then find the actual Trip objects and
        //create a List of Trip
        String[] trips = request.getParameterValues("tripsBox");
        List<Trip> allTrips = service.findAllTrips();
        List<Trip> tripsForTraveler = new ArrayList<>();
        if (trips != null) {
            for (Trip t : allTrips) {
                for (String s : trips) {
                    if (t.getTripId() == Integer.parseInt(s)) {
                        tripsForTraveler.add(t);
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
        String fileName = firstName + " " + lastName + ".jpg";
        
        //Remove spaces from the fileName and replace with '-'
        fileName = fileName.replace(' ', '-');
        
        //Add everything to the object
        newT.setTravelerId(Integer.parseInt(id));
        newT.setFirstName(firstName);
        newT.setLastName(lastName);
        newT.setAddress(address);
        newT.setCity(city);
        newT.setState(state);
        newT.setZip(zip);
        newT.setBirthDate(birthDate);
        newT.setTrips(tripsForTraveler);
        newT.setPhotoLink(urlFromForm);
        newT.setPhotoFilePath(fileName);
        
        service.addUpdateTraveler(newT);
        
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
        
        //Go back to travelersHome
        return "redirect:/travelersHome";
    }
    
    @GetMapping("/travelersDelete")
    public String deleteTraveler(@RequestParam Integer id) {
        service.deleteTravelerByID(id);
        return "redirect:/travelersHome";
    }
}
