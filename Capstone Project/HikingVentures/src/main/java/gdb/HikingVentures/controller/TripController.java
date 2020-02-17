package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.Equipment;
import gdb.HikingVentures.entities.Location;
import gdb.HikingVentures.entities.Trail;
import gdb.HikingVentures.entities.Traveler;
import gdb.HikingVentures.entities.Trip;
import gdb.HikingVentures.service.HVService;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * Capstone Project
 * @author garrettbecker
 * For opening Trip web pages
 */

@Controller
public class TripController {
    @Autowired
    HVService service;
    
    //Open tripsHome.html
    @RequestMapping("/tripsHome")
    public String openTripsHome() {
        return "/tripsHome";
    }
    
    //Open tripsAdd.html
    @RequestMapping("/tripsAdd")
    public String openTripsAdd(Model model) {
        //Add the Trail options to the page
        List<Trail> allTrails = service.findAllTrails();
        model.addAttribute("trails", allTrails);
        
        //Add the Equipment options to the page
        List<Equipment> allEquipment = service.findAllEquipment();
        model.addAttribute("equipment", allEquipment);
        
        //Add the Equipment options to the page
        List<Traveler> allTravelers = service.findAllTravelers();
        model.addAttribute("travelers", allTravelers);
        
        return "/tripsAdd";
    }
    
    //Actually add a new trail to the db
    @PostMapping("/addNewTrip")
    public String addNewTrip(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException{
        Trip newT = new Trip();
        String tripName = request.getParameter("tripNameBox");
        String startDate = request.getParameter("startDateBox");
        String endDate = request.getParameter("endDateBox");
        String cost = request.getParameter("costBox");
        
        //Trail - get as String array, then find the actual Trail object
        //create list to add to the Trip
        String[] trails = request.getParameterValues("trailBox");
        List<Trail> allTrails = service.findAllTrails();
        List<Trail> trailsForTrip = new ArrayList<>();
        if (trails != null) {
            for (Trail t : allTrails) {
                for (String s : trails) {
                    if (t.getTrailId() == Integer.parseInt(s)) {
                        trailsForTrip.add(t);
                    }
                }
            }
        }
        
        //Equipment - get as String array, then find the actual Equipment object
        //create list to add to the Trip
        String[] equipment = request.getParameterValues("equipmentBox");
        List<Equipment> allEquipment = service.findAllEquipment();
        List<Equipment> equipmentForTrip = new ArrayList<>();
        if (equipment != null) {
            for (Equipment e : allEquipment) {
                for (String s : equipment) {
                    if (e.getEquipmentId() == Integer.parseInt(s)) {
                        equipmentForTrip.add(e);
                    }
                }
            }
        }
        
        //Traveler - get as String array, then find the actual Traveler object
        //create list to add to the Trip
        String[] travelers = request.getParameterValues("travelerBox");
        List<Traveler> allTravelers = service.findAllTravelers();
        List<Traveler> travelersForTrip = new ArrayList<>();
        if (travelers != null) {
            for (Traveler t : allTravelers) {
                for (String s : travelers) {
                    if (t.getTravelerId() == Integer.parseInt(s)) {
                        travelersForTrip.add(t);
                    }
                }
            }
        }
        
        //Add everything to the object
        newT.setTripName(tripName);
        newT.setStartDate(LocalDate.parse(startDate));
        newT.setEndDate(LocalDate.parse(endDate));
        newT.setTripCostPerTraveler(Double.parseDouble(cost));
        newT.setTrails(trailsForTrip);
        newT.setEquipment(equipmentForTrip);
        newT.setTravelers(travelersForTrip);
        
        service.addUpdateTrip(newT);
      
        //Go back to travelersHome
        return "redirect:/tripsHome";
    }
    
    //View specific details for an equipment item
    @GetMapping("/tripsViewDetails")
    public String openTripViewDetails(@RequestParam Integer id, Model model) {
        Trip t = service.findTripByID(id);
        model.addAttribute("trip", t);
        
        List<Traveler> travelers = t.getTravelers();
        model.addAttribute("travelers", travelers);
        
        List<Equipment> equipment = t.getEquipment();
        model.addAttribute("equipment", equipment);
        
        List<Trail> trails = t.getTrails();
        model.addAttribute("trails", trails);
        
        try {
            Trail tr = trails.get(0);
            Location loc = tr.getLocation();
            model.addAttribute("location", loc);
        } catch (IndexOutOfBoundsException e) {
            
        }
        
        return "tripsViewDetails";
    }
    
    //Open page for editing a specific trip
    @GetMapping("/tripsEdit")
    public String openTripsEditDetails(@RequestParam Integer id, Model model) {
        Trip t = service.findTripByID(id);
        model.addAttribute("trip", t);
        
        //Add the Trail options to the page
        List<Trail> allTrails = service.findAllTrails();
        model.addAttribute("trails", allTrails);
        
        //Add the Equipment options to the page
        List<Equipment> allEquipment = service.findAllEquipment();
        model.addAttribute("equipment", allEquipment);
        
        //Add the Equipment options to the page
        List<Traveler> allTravelers = service.findAllTravelers();
        model.addAttribute("travelers", allTravelers);
        
        return "tripsEdit";
    }
    
    //Actually editing a trip
    @PostMapping("/editTrip")
    public String editTrip(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException{
        Trip newT = new Trip();
        String id = request.getParameter("id");
        String tripName = request.getParameter("tripNameBox");
        String startDate = request.getParameter("startDateBox");
        String endDate = request.getParameter("endDateBox");
        String cost = request.getParameter("costBox");
        
        //Trail - get as String array, then find the actual Trail object
        //create list to add to the Trip
        String[] trails = request.getParameterValues("trailBox");
        List<Trail> allTrails = service.findAllTrails();
        List<Trail> trailsForTrip = new ArrayList<>();
        if (trails != null) {
            for (Trail t : allTrails) {
                for (String s : trails) {
                    if (t.getTrailId() == Integer.parseInt(s)) {
                        trailsForTrip.add(t);
                    }
                }
            }
        }
        
        //Equipment - get as String array, then find the actual Equipment object
        //create list to add to the Trip
        String[] equipment = request.getParameterValues("equipmentBox");
        List<Equipment> allEquipment = service.findAllEquipment();
        List<Equipment> equipmentForTrip = new ArrayList<>();
        if (equipment != null) {
            for (Equipment e : allEquipment) {
                for (String s : equipment) {
                    if (e.getEquipmentId() == Integer.parseInt(s)) {
                        equipmentForTrip.add(e);
                    }
                }
            }
        }
        
        //Traveler - get as String array, then find the actual Traveler object
        //create list to add to the Trip
        String[] travelers = request.getParameterValues("travelerBox");
        List<Traveler> allTravelers = service.findAllTravelers();
        List<Traveler> travelersForTrip = new ArrayList<>();
        if (travelers != null) {
            for (Traveler t : allTravelers) {
                for (String s : travelers) {
                    if (t.getTravelerId() == Integer.parseInt(s)) {
                        travelersForTrip.add(t);
                    }
                }
            }
        }
        
        //Add everything to the object
        newT.setTripId(Integer.parseInt(id));
        newT.setTripName(tripName);
        newT.setStartDate(LocalDate.parse(startDate));
        newT.setEndDate(LocalDate.parse(endDate));
        newT.setTripCostPerTraveler(Double.parseDouble(cost));
        newT.setTrails(trailsForTrip);
        newT.setEquipment(equipmentForTrip);
        newT.setTravelers(travelersForTrip);
        
        service.addUpdateTrip(newT);
      
        //Go back to travelersHome
        return "redirect:/tripsHome";
    }
    
    //Delete methods
    @GetMapping("/tripsDelete")
    public String deleteTrip(@RequestParam Integer id) {
        service.deleteTripByID(id);
        return "redirect:/tripsHome";
    }
}
