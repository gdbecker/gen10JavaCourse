package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.DifficultyRating;
import gdb.HikingVentures.entities.Location;
import gdb.HikingVentures.entities.RouteType;
import gdb.HikingVentures.entities.Trail;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capstone Project
 * @author garrettbecker
 * 
 * This Controller focuses on managing all pages relating to the Trail object
 * 
 * Methods:
 * -openTrailsHome()
 * Opens trailsHome.html
 * 
 * -openTrailsAdd(Model model)
 * Opens trailsAdd.html
 * 
 * -addNewEquipment(HttpServletRequest request, HttpServletResponse response, Model model)
 * Actually add a new trail to the db
 * 
 * -openTrailViewDetails(@RequestParam Integer id, Model model)
 * View specific details for a trail
 * 
 * -openTrailsEditDetails(@RequestParam Integer id, Model model)
 * Open page for editing a specific trail
 * 
 * -editTrail(HttpServletRequest request, HttpServletResponse response, Model model)
 * Actually add a new trail to the db
 * 
 * -deleteTrail(@RequestParam Integer id)
 * Deleting a trail from the db
 */

@Controller
public class TrailController {
    @Autowired
    HVService service;
    
    @RequestMapping("/trailsHome")
    public String openTrailsHome() {
        return "/trailsHome";
    }
    
    @RequestMapping("/trailsAdd")
    public String openTrailsAdd(Model model) {
        //Add the different location options to the page
        List<Location> allLocations = service.findAllLocations();
        model.addAttribute("locations", allLocations);
        
        //Add the route types to the page
        List<RouteType> rt = service.findAllRouteTypes();
        model.addAttribute("routeTypes", rt);
        
        //Add the difficulty ratings to the page
        List<DifficultyRating> dr = service.findAllDifficultyRatings();
        model.addAttribute("ratings", dr);
        
        //Add the Trip options to the page
        List<Trip> allTrips = service.findAllTrips();
        model.addAttribute("trips", allTrips);
        
        return "/trailsAdd";
    }
    
    @PostMapping("/addNewTrail")
    public String addNewEquipment(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException{
        Trail newT = new Trail();
        String trailName = request.getParameter("trailNameBox");
        
        //Location - get as String array, then find the actual Location object
        //and add it to the Location to add to the new Trail
        String[] locations = request.getParameterValues("locationBox");
        List<Location> allLocations = service.findAllLocations();
        Location locationForTrail = new Location();
        if (locations != null) {
            for (Location l : allLocations) {
                for (String s : locations) {
                    if (l.getLocationId() == Integer.parseInt(s)) {
                        locationForTrail = l;
                    }
                }
            }
        }
        
        //RouteType - get as String array, then find the actual RouteType object
        //and add it to the RouteType to add to the new Trail
        String[] routeTypesString = request.getParameterValues("routeTypeBox");
        List<RouteType> allRT = service.findAllRouteTypes();
        RouteType rtForTrail = new RouteType();
        if (routeTypesString != null) {
            for (RouteType r : allRT) {
                for (String s : routeTypesString) {
                    if (r.getRouteTypeId() == Integer.parseInt(s)) {
                        rtForTrail = r;
                    }
                }
            }
        }
        
        //DifficultyRating - get as String array, then find the actual DifficultyRating object
        //and add it to the DifficultyRating to add to the new Trail
        String[] ratingsString = request.getParameterValues("difficultyRatingBox");
        List<DifficultyRating> allDR = service.findAllDifficultyRatings();
        DifficultyRating drForTrail = new DifficultyRating();
        if (ratingsString != null) {
            for (DifficultyRating d : allDR) {
                for (String s : ratingsString) {
                    if (d.getDifficultyRatingId() == Integer.parseInt(s)) {
                        drForTrail = d;
                    }
                }
            }
        }
        
        String distance = request.getParameter("distanceBox");
        String elevationGain = request.getParameter("elevationGainBox");
        
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
        
        //Map PDF URL
        String mapUrl = request.getParameter("filePathMap");
        
        //Image URL
        String urlFromForm = request.getParameter("filePath");
        URL url = null;
        if (!urlFromForm.equals("")) {
            url = new URL(urlFromForm);
        }
        String fileName = trailName + ".jpg";
        
        //Remove spaces from the fileName and replace with '-'
        fileName = fileName.replace(' ', '-');
        
        //Add everything to the object
        newT.setTrailName(trailName);
        newT.setLocation(locationForTrail);
        newT.setRouteType(rtForTrail);
        newT.setDifficultyRating(drForTrail);
        newT.setDistance(Double.parseDouble(distance));
        newT.setElevationGain(Double.parseDouble(elevationGain));
        newT.setTrips(tripsForTraveler);
        newT.setMapLink(mapUrl);
        newT.setPhotoLink(urlFromForm);
        newT.setPhotoFilePath(fileName);
        
        service.addUpdateTrail(newT);
        
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
        return "redirect:/trailsHome";
    }
    
    @GetMapping("/trailsViewDetails")
    public String openTrailViewDetails(@RequestParam Integer id, Model model) {
        Trail thisTrail = service.findTrailByID(id);
        model.addAttribute("trail", thisTrail);
        
        //Only get location that contains the trail
        Location loc = thisTrail.getLocation();
        model.addAttribute("location", loc);
        
        //Get route type for the trail
        RouteType rt = thisTrail.getRouteType();
        model.addAttribute("routeType", rt);
        
        //Get difficulty rating for the trail
        DifficultyRating dr = thisTrail.getDifficultyRating();
        model.addAttribute("difficultyRating", dr);
        
        //Only get trips that contain the trail
        List<Trip> tripsWithTrail = thisTrail.getTrips();
        model.addAttribute("trips", tripsWithTrail);
        
        return "trailsViewDetails";
    }
    
    @GetMapping("/trailsEdit")
    public String openTrailsEditDetails(@RequestParam Integer id, Model model) {
        Trail t = service.findTrailByID(id);
        model.addAttribute("trail", t);
        
        //Add the different location options to the page
        List<Location> allLocations = service.findAllLocations();
        model.addAttribute("locations", allLocations);
        
        //Add the route types to the page
        List<RouteType> rt = service.findAllRouteTypes();
        model.addAttribute("routeTypes", rt);
        
        //Add the difficulty ratings to the page
        List<DifficultyRating> dr = service.findAllDifficultyRatings();
        model.addAttribute("ratings", dr);
        
        //Add the Trip options to the page
        List<Trip> allTrips = service.findAllTrips();
        model.addAttribute("trips", allTrips);
        
        return "trailsEdit";
    }
    
    @PostMapping("/editTrail")
    public String editTrail(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException{
        Trail newT = new Trail();
        String id = request.getParameter("id");
        String trailName = request.getParameter("trailNameBox");
        
        //Location - get as String array, then find the actual Location object
        //and add it to the Location to add to the new Trail
        String[] locations = request.getParameterValues("locationBox");
        List<Location> allLocations = service.findAllLocations();
        Location locationForTrail = new Location();
        if (locations != null) {
            for (Location l : allLocations) {
                for (String s : locations) {
                    if (l.getLocationId() == Integer.parseInt(s)) {
                        locationForTrail = l;
                    }
                }
            }
        }
        
        //RouteType - get as String array, then find the actual RouteType object
        //and add it to the RouteType to add to the new Trail
        String[] routeTypesString = request.getParameterValues("routeTypeBox");
        List<RouteType> allRT = service.findAllRouteTypes();
        RouteType rtForTrail = new RouteType();
        if (routeTypesString != null) {
            for (RouteType r : allRT) {
                for (String s : routeTypesString) {
                    if (r.getRouteTypeId() == Integer.parseInt(s)) {
                        rtForTrail = r;
                    }
                }
            }
        }
        
        //DifficultyRating - get as String array, then find the actual DifficultyRating object
        //and add it to the DifficultyRating to add to the new Trail
        String[] ratingsString = request.getParameterValues("difficultyRatingBox");
        List<DifficultyRating> allDR = service.findAllDifficultyRatings();
        DifficultyRating drForTrail = new DifficultyRating();
        if (ratingsString != null) {
            for (DifficultyRating d : allDR) {
                for (String s : ratingsString) {
                    if (d.getDifficultyRatingId() == Integer.parseInt(s)) {
                        drForTrail = d;
                    }
                }
            }
        }
        
        String distance = request.getParameter("distanceBox");
        String elevationGain = request.getParameter("elevationGainBox");
        
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
        
        //Map PDF URL
        String mapUrl = request.getParameter("filePathMap");
        
        //Image URL
        String urlFromForm = request.getParameter("filePath");
        URL url = null;
        if (!urlFromForm.equals("")) {
            url = new URL(urlFromForm);
        }
        String fileName = trailName + ".jpg";
        
        //Remove spaces from the fileName and replace with '-'
        fileName = fileName.replace(' ', '-');
        
        //Add everything to the object
        newT.setTrailId(Integer.parseInt(id));
        newT.setTrailName(trailName);
        newT.setLocation(locationForTrail);
        newT.setRouteType(rtForTrail);
        newT.setDifficultyRating(drForTrail);
        newT.setDistance(Double.parseDouble(distance));
        newT.setElevationGain(Double.parseDouble(elevationGain));
        newT.setTrips(tripsForTraveler);
        newT.setMapLink(mapUrl);
        newT.setPhotoLink(urlFromForm);
        newT.setPhotoFilePath(fileName);
        
        service.addUpdateTrail(newT);
        
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
        return "redirect:/trailsHome";
    }
    
    @GetMapping("/trailsDelete")
    public String deleteTrail(@RequestParam Integer id) {
        service.deleteTrailByID(id);
        return "redirect:/trailsHome";
    }
}
