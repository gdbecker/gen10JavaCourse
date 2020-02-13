package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.Traveler;
import gdb.HikingVentures.entities.Trip;
import gdb.HikingVentures.service.HVService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    public String openTravelersAdd(Model model) {
        //Add the different Trip options to the page for the user to select from
        List<Trip> allTrips = service.findAllTrips();
        model.addAttribute("trips", allTrips);
        
        return "/travelersAdd";
    }
    
    //Actually add a new traveler to the db
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
        LocalDate birthDate = LocalDate.parse(birth);
        
        //Trips - get as String array, then find the actual Trip objects and
        //create a List of Trip
        String[] trips = request.getParameterValues("tripsBox");
        List<Trip> allTrips = service.findAllTrips();
        List<Trip> tripsForTraveler = new ArrayList<>();
        if (!trips.equals(null)) {
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
        URL url = new URL(urlFromForm);
        String fileName = firstName + lastName + ".jpg";
        
        
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
        
        //Remove spaces from the fileName and replace with '-'
        fileName = fileName.replace(' ', '-');
        newT.setPhotoFilePath(fileName);
        
        service.addUpdateTraveler(newT);
        
        //Save the image from url as a file in directory
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream("src/main/resources/static/img/" + fileName);
        
        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        
        //Go back to travelersHome
        return "redirect:/travelersHome";
    }
    
    //View specific details for a traveler
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
    
    //Open page for editing a specific traveler
    @GetMapping("/travelersEdit")
    public String openTravelersEditDetails(@RequestParam Integer id, Model model) {
        Traveler t = service.findTravelerByID(id);
        model.addAttribute("traveler", t);
       
        List<Trip> trips = service.findAllTrips();
        model.addAttribute("trips", trips);
        
        return "travelersEdit";
    }
    
    //Actually editing a traveler
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
        LocalDate birthDate = LocalDate.parse(birth);
        
        //Trips - get as String array, then find the actual Trip objects and
        //create a List of Trip
        String[] trips = request.getParameterValues("tripsBox");
        List<Trip> allTrips = service.findAllTrips();
        List<Trip> tripsForTraveler = new ArrayList<>();
        if (!trips.equals(null)) {
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
        URL url = new URL(urlFromForm);
        String fileName = firstName + lastName + ".jpg";
        
        
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
        
        //Remove spaces from the fileName and replace with '-'
        fileName = fileName.replace(' ', '-');
        newT.setPhotoFilePath(fileName);
        
        service.addUpdateTraveler(newT);
        
        //Save the image from url as a file in directory
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream("src/main/resources/static/img/" + fileName);
        
        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        
        //Go back to travelersHome
        return "redirect:/travelersHome";
    }
    
    //Delete methods
    @GetMapping("/travelersDelete")
    public String deleteTraveler(@RequestParam Integer id) {
        service.deleteTravelerByID(id);
        return "redirect:/travelersHome";
    }
}
