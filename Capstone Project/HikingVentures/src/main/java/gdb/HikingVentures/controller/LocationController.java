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
 * For opening Location web pages
 */

@Controller
public class LocationController {
    @Autowired
    HVService service;
    
    ImageServlet is = new ImageServlet();
    
    //Locations Home
    @RequestMapping("/locationsHome")
    public String openLocationsHome() {
        return "/locationsHome";
    }
    
    //Go to "Add a Location" page
    @RequestMapping("/locationsAdd")
    public String openLocationsAdd() {
        return "/locationsAdd";
    }
    
    //Actually add a new location to the db
    @PostMapping("/addNewLocation")
    public String addNewLocation(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException{
        Location newL = new Location();
        String parkName = request.getParameter("parkNameBox");
        String nearbyCity = request.getParameter("nearbyCityBox");
        String state = request.getParameter("stateBox");
        String urlFromForm = request.getParameter("filePath");
        URL url = new URL(urlFromForm);
        String fileName = parkName + ".jpg";
        //Part filePart = request.getPart("filePath");
        //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        
        newL.setParkName(parkName);
        newL.setNearbyCity(nearbyCity);
        newL.setState(state);
        
        //Remove spaces from the fileName and replace with '-'
        fileName = fileName.replace(' ', '-');
                
        newL.setPhotoLink(fileName);
        
        service.addUpdateLocation(newL);
        
        //Save the image from url as a file in directory
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream("src/main/resources/static/img/" + fileName);
        
        byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
		os.write(b, 0, length);
            }
        
        //Go back to locationsHome
        return "redirect:/locationsHome";
    }
    
    //View specific details for a location
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
    
    //Edit a specific location
    @GetMapping("/locationsEdit")
    public String openLocationsEditDetails(@RequestParam Integer id, Model model) {
        Location l = service.findLocationByID(id);
        model.addAttribute("location", l);
        return "locationsEdit";
    }
    
    //Delete methods
    @GetMapping("/locationsDelete")
    public String deleteLocation(@RequestParam Integer id) {
        service.deleteLocationByID(id);
        return "redirect:/locationsHome";
    }
    
    @GetMapping("/locationsDeleteFromView")
    public String deleteLocationFromView(@RequestParam Integer id) {
        //Delete trails and trips here
        
        service.deleteLocationByID(id);
        return "redirect:/locationsHome";
    }
    
    //Go to About page (used for all controllers)
    @RequestMapping("/about")
    public String openAbout() {
        return "/about";
    }
}
