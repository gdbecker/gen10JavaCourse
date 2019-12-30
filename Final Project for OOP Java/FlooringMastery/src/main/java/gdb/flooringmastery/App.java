package gdb.flooringmastery;

import gdb.flooringmastery.controller.FlooringMasteryController;
import gdb.flooringmastery.dao.FlooringMasteryDao;
import gdb.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import gdb.flooringmastery.service.FlooringMasteryService;
import gdb.flooringmastery.service.FlooringMasteryServiceImpl;
import gdb.flooringmastery.ui.FlooringMasteryView;
import gdb.flooringmastery.ui.UserIO;
import gdb.flooringmastery.ui.UserIOConsoleImpl;

/**
 * Final Project OOP
 * @author garrettbecker
 */

public class App {
    public static void main(String[] args) {
        //Instantiate the UserIO
        UserIO myIO = new UserIOConsoleImpl();
        
        //Instantiate the view
        FlooringMasteryView myView = new FlooringMasteryView(myIO);
        
        //Instantiate DAO
        FlooringMasteryDao mainDao = new FlooringMasteryDaoFileImpl();
        
        //Instantiate Service
        FlooringMasteryService myService = new FlooringMasteryServiceImpl(mainDao);
        
        //Instantiate the Controller
        FlooringMasteryController myController = new FlooringMasteryController(myService, myView);
        
        //Run the program
        myController.run();
    } 
}
