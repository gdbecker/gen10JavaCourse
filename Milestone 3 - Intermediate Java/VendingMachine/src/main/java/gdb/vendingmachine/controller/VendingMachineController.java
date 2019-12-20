package gdb.vendingmachine.controller;

import gdb.vendingmachine.service.VendingMachineService;
import gdb.vendingmachine.service.VendingMachineServiceImpl;
import gdb.vendingmachine.ui.VendingMachineView;

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

public class VendingMachineController {
    VendingMachineService service;
    VendingMachineView view;

    //Constructor for mkaing a Controller
    public VendingMachineController(VendingMachineService service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }
    
    //Main run function
    public void run() {
        
    }
    
    
}
