package gdb.HikingVentures.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Capstone Project
 * @author garrettbecker
 * 
 * This Controller focuses on managing the opening pages of the application. Also
 * manages getting to the About page
 * 
 * -showLoginForm()
 * Upon opening the application, this method takes the user directly to the login
 * page.
 * 
 * -displayIndexPage(HttpServletRequest request)
 * Upon hitting the "LOG IN" button on the login page, the user is directed to
 * the tripsHome.html page where they can start interacting with the app.
 * 
 * -openAbout()
 * Go to About page
 */

@Controller
public class LoginController {
    
    @GetMapping({"/", ""})
    public String showLoginForm() {
        return "login";
    }
    
    @GetMapping("/login")
    public String displayIndexPage(HttpServletRequest request) {
        /*
        if (request.getParameter("login_error").equals("1")) {
            return "login";
        } else {
            return "index";
        } */
        
        return "tripsHome";
    }
    
    @RequestMapping("/about")
    public String openAbout() {
        return "/about";
    }
    
}
