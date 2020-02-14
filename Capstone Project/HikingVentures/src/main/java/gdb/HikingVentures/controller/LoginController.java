package gdb.HikingVentures.controller;

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
    
}
