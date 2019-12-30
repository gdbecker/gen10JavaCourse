package gdb.flooringmastery.controller;

import gdb.flooringmastery.dao.FlooringMasteryPersistenceException;
import gdb.flooringmastery.dto.Order;
import gdb.flooringmastery.service.FlooringMasteryService;
import gdb.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.util.List;

/**
 * Final Project OOP
 * @author garrettbecker
 */

public class FlooringMasteryController {
    FlooringMasteryService service;
    FlooringMasteryView view;
    
    //Constructor for creating a controller
    public FlooringMasteryController(FlooringMasteryService service, FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }
    
    //Main method for running the application
    public void run() {
        //Load Order, Product, and Tax data into memory
        try {
            loadFileData(); 
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        //Instantiate key variables to control staying in program
        boolean keepGoing = true;
        int menuSelection = 0;
        
        //Main structure to keep program looping through
        try {
            while (keepGoing) {
                menuSelection = displayMainMenuGetSelection();
                
                switch(menuSelection) {
                    case 1:   
                        displayOrdersByDate();
                        break;
                    case 2:
                        addNewOrder();
                        break;
                    case 3:
                        //editOrder();
                        break;
                    case 4:
                        //removeOrder();
                        break;
                    case 5:
                        
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        keepGoing = false;
                        unknownCommand();
                }
            }
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        //Write Order data from memory back to the file
        try {
            writeFileData(); 
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        exitMessage();
    }
    
    public int displayMainMenuGetSelection() {
        return view.displayMainMenuGetSelection();
    }
    
    //Option 1: Display orders by date input
    private void displayOrdersByDate() throws FlooringMasteryPersistenceException {
        LocalDate dateInput = view.getOrderDateInputOption1();
        List<Order> orderList = service.getOrdersByDate(dateInput);
        view.displayOrders(orderList);
    }
    
    //Option 2: Add an order
    private void addNewOrder() throws FlooringMasteryPersistenceException {
        int highestOrderNum = service.returnHighestOrderNumber();
        Order newOrder = view.addOrder(highestOrderNum);
        Order updatedNewOrder = service.fillRemainingOrderDetailsFromAddOrder(newOrder);
        service.addOrder(updatedNewOrder);
    }
    
    //Option 3: Edit an order
    private void editOrder() throws FlooringMasteryPersistenceException {
        
    }
    
    //Option 4: Remove an order
    private void removeOrder() throws FlooringMasteryPersistenceException {
        
    }
    
    //Load Order, Product, and Tax info from files
    private void loadFileData() throws FlooringMasteryPersistenceException {
        service.loadFileData();
    }
    
    //Write Order data from memory to file
    private void writeFileData() throws FlooringMasteryPersistenceException {
        service.writeFileData();
    }
    
    //Other messages to print if needed
    private void unknownCommand() {
        view.displayUnknownCommandMessage();
    }
    
    private void exitMessage() {
        view.displayExitMessage();
    }  
    
}
