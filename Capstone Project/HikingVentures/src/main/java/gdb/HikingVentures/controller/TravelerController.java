package gdb.HikingVentures.controller;

import gdb.HikingVentures.service.HVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capstone Project
 * @author garrettbecker
 */

@RestController
public class TravelerController {
    @Autowired
    HVService service;
}
