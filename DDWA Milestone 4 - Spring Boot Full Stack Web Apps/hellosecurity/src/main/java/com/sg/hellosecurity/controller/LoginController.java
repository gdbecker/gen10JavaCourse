package com.sg.hellosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @date Friday January 31, 2020
 * @author garrettbecker
 */

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
