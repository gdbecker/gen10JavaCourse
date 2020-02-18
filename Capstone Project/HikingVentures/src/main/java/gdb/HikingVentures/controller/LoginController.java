package gdb.HikingVentures.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Capstone Project
 * @author garrettbecker
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
    
}
