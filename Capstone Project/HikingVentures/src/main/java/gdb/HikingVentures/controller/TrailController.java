package gdb.HikingVentures.controller;

import gdb.HikingVentures.service.HVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Capstone Project
 * @author garrettbecker
 */

@Controller
public class TrailController {
    @Autowired
    HVService service;
}
