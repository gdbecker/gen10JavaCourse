package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.Location;
import gdb.HikingVentures.entities.Trail;
import gdb.HikingVentures.entities.Trip;
import gdb.HikingVentures.service.HVService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capstone Project
 * @author garrettbecker
 * 
 * This Controller focuses on managing all pages relating to the Location object
 * 
 * Methods:
 * -openLocationsHome()
 * Opens locationsHome.html
 * 
 * -openLocationsAdd()
 * Opens locationsAdd.html
 * 
 * -addNewLocation(HttpServletRequest request, HttpServletResponse response, Model model)
 * Actually adds a new Location object to the db
 * 
 * -openLocationsViewDetails(@RequestParam Integer id, Model model)
 * View specific details for a Location object
 * 
 * -openLocationsEditDetails(@RequestParam Integer id, Model model)
 * Open page for editing a specific Location object
 * 
 * -editLocation(HttpServletRequest request, HttpServletResponse response, Model model)
 * Actually editing a Location object
 * 
 * -deleteLocation(@RequestParam Integer id)
 * Deleting a Location object
 */

@Controller
public class LocationController {
    @Autowired
    HVService service;
        
    @RequestMapping("/locationsHome")
    public String openLocationsHome() {
        return "/locationsHome";
    }
    
    @RequestMapping("/locationsAdd")
    public String openLocationsAdd() {
        return "/locationsAdd";
    }
    
    @PostMapping("/addNewLocation")
    public String addNewLocation(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException{
        Location newL = new Location();
        String parkName = request.getParameter("parkNameBox");
        String nearbyCity = request.getParameter("nearbyCityBox");
        String state = request.getParameter("stateBox");
        
        //Image URL
        String urlFromForm = request.getParameter("filePath");
        URL url = null;
        if (!urlFromForm.equals("")) {
            url = new URL(urlFromForm);
        }
        String fileName = parkName + ".jpg";
        
        //Remove spaces from the fileName and replace with '-'
        fileName = fileName.replace(' ', '-');
        
        newL.setParkName(parkName);
        newL.setNearbyCity(nearbyCity);
        newL.setState(state);
        newL.setPhotoLink(urlFromForm); 
        newL.setPhotoFilePath(fileName);
        
        service.addUpdateLocation(newL);
        
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
        
        //Go back to locationsHome
        return "redirect:/locationsHome";
    }
    
    @GetMapping("/locationsViewDetails")
    public String openLocationsViewDetails(@RequestParam Integer id, Model model) {
        Location l = service.findLocationByID(id);
        model.addAttribute("location", l);
        
        List<Trail> trails = service.findAllTrailsByLocationID(id);
        model.addAttribute("trails", trails);
        
        List<Trip> trips = service.findAllTripsByLocationID(id);
        model.addAttribute("trips", trips);
        
        return "locationsViewDetails";
    }
    
    @GetMapping("/locationsEdit")
    public String openLocationsEditDetails(@RequestParam Integer id, Model model) {
        Location l = service.findLocationByID(id);
        model.addAttribute("location", l);
        return "locationsEdit";
    }
    
    @PostMapping("/editLocation")
    public String editLocation(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException{
        Location l = new Location();
        String id = request.getParameter("id");
        String parkName = request.getParameter("parkNameBox");
        String nearbyCity = request.getParameter("nearbyCityBox");
        String state = request.getParameter("stateBox");
        String urlFromForm = request.getParameter("filePath");
        URL url = null;
        String fileName = null;
        
        if (!urlFromForm.equals("")) {
            url = new URL(urlFromForm);
            fileName = parkName + ".jpg";
            
            //Remove spaces from the fileName and replace with '-'
            fileName = fileName.replace(' ', '-');
        }

        l.setLocationId(Integer.parseInt(id));
        l.setParkName(parkName);
        l.setNearbyCity(nearbyCity);
        l.setState(state);
        l.setPhotoLink(urlFromForm);
        l.setPhotoFilePath(fileName);
        
        service.addUpdateLocation(l);
        
        //Save the image from url as a file in directory
        //Only if there actually was a URL inputted
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
 
        //Go back to locationsHome
        return "redirect:/locationsHome";
    }
    
    @GetMapping("/locationsDelete")
    public String deleteLocation(@RequestParam Integer id) {
        //Delete Trips associated with the Location
        /*List<Trip> allTrips = service.findAllTrips();
        for (Trip t : allTrips) {
            List<Trail> trailsInTrip = t.getTrails();
            for (Trail tr : trailsInTrip) {
                Location l = tr.getLocation();
                int locationId = l.getLocationId();
                if (locationId == id) {
                    service.deleteTripByID(t.getTripId());
                }
            }
        }*/
        
        //Delete Trails associated with the Location
        List<Trail> allTrails = service.findAllTrails();
        for (Trail tr : allTrails) {
            Location l = tr.getLocation();
            int locationId = l.getLocationId();
            if (locationId == id) {
                service.deleteTrailByID(tr.getTrailId());
            }
        }
        
        //Delete the actual Location
        service.deleteLocationByID(id);
        return "redirect:/locationsHome";
    }
}
