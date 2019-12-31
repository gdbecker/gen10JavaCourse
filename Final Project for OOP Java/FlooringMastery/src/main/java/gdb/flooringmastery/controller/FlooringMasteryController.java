package gdb.flooringmastery.controller;

import gdb.flooringmastery.dao.FlooringMasteryPersistenceException;
import gdb.flooringmastery.dto.Order;
import gdb.flooringmastery.dto.Product;
import gdb.flooringmastery.dto.Tax;
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
        //Instantiate key variables to control staying in program
        boolean keepGoing = true;
        int mode = 0;
        int menuSelection = 0;
        
        //Import either Production order data or Training order data
        //Both options will import Product and Tax information
        try {
            mode = displayModeMenuGetSelection();
                
            switch(mode) {
                case 1: //Production Mode
                    loadFileData(mode);
                    break;
                case 2: //Training Mode
                    loadFileData(mode);
                    break;
                default:
                    unknownCommand();
            }
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        //Main structure to keep program looping through
        try {
            while (keepGoing) {
                menuSelection = displayMainMenuGetSelection(mode);
                switch(mode) {
                    case 1:
                        switch(menuSelection) {
                            case 1:   
                                displayAllOrders();
                                break;
                            case 2:   
                                displayOrdersByDate();
                                break;
                            case 3:
                                addNewOrder();
                                break;
                            case 4:
                                editOrder();
                                break;
                            case 5:
                                removeOrder();
                                break;
                            case 6:
                                saveCurrentWork(mode);
                                break;
                            case 7:
                                keepGoing = false;
                                break;
                            default:
                                keepGoing = false;
                                unknownCommand();
                        }
                        break;
                    case 2:
                        switch(menuSelection) {
                            case 1:   
                                displayAllOrders();
                                break;
                            case 2:   
                                displayOrdersByDate();
                                break;
                            case 3:
                                addNewOrder();
                                break;
                            case 4:
                                editOrder();
                                break;
                            case 5:
                                removeOrder();
                                break;
                            case 6:
                                keepGoing = false;
                                break;
                            default:
                                keepGoing = false;
                                unknownCommand();
                        }
                        break;
                    default:
                        unknownCommand();
                }
                
            }
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        //Write Order data from memory back to the file, only in Production mode
        try {
            switch(mode) {
                case 1: //Production Mode
                    writeFileData(mode);
                    break;
                case 2: //Training Mode
                    //Do nothing
                    break;
                default:
                    unknownCommand();
            }
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        exitMessage();
    }
    public int displayModeMenuGetSelection() {
        return view.displayModeMenuGetSelection();
    }
    
    public int displayMainMenuGetSelection(int mode) {
        return view.displayMainMenuGetSelection(mode);
    }
    
    //Option 1: Display all orders
    private void displayAllOrders() throws FlooringMasteryPersistenceException {
        List<Order> orderList = service.getAllOrders();
        view.displayOrders(orderList);
    }
    
    //Option 2: Display orders by date input
    private void displayOrdersByDate() throws FlooringMasteryPersistenceException {
        LocalDate dateInput = view.getOrderDateInputOption2();
        List<Order> orderList = service.getOrdersByDate(dateInput);
        view.displayOrders(orderList);
    }
    
    //Option 3: Add an order
    private void addNewOrder() throws FlooringMasteryPersistenceException {
        int highestOrderNum = service.returnHighestOrderNumber();
        int removedOrderNumWasHighest = service.returnRemovedOrderNumWasHighest();
        List<Product> productList = service.getAllProducts();
        List<Tax> taxList = service.getAllTaxes();
        Order newOrder = view.addOrder(highestOrderNum, removedOrderNumWasHighest, productList, taxList);
        
        //Check to make sure that newOrder was confirmed by user to commit
        //If so, add it to memory
        //If not, don't do anything with it
        if (newOrder.getOrderNumber() != -1) {
            Order updatedNewOrder = service.fillRemainingOrderDetailsFromAddOrder(newOrder);
            service.addOrder(updatedNewOrder);
        } else {
            //Don't do anything with this uncommited order
        }
    }
    
    //Option 4: Edit an order
    private void editOrder() throws FlooringMasteryPersistenceException {
        LocalDate dateInput = view.getOrderDateInputOption4();
        String customerNameInput = view.getCustomerNameInputOption4();
        List<Product> productList = service.getAllProducts();
        List<Tax> taxList = service.getAllTaxes();
        Order orderToEdit = service.getOrderByNameAndDate(customerNameInput, dateInput);
        
        //Make sure that orderToEdit is a real order from memory
        //If so, go ahead and edit it
        //If not, don't do anything with it
        if (orderToEdit.getOrderNumber() != -1) {
            Order editedOrder = view.editOrder(orderToEdit, productList, taxList);
            Order updatedEditedOrder = service.fillRemainingOrderDetailsFromAddOrder(editedOrder);
            service.removeOrder(orderToEdit.getOrderNumber());
            service.addOrder(updatedEditedOrder);
        } else {
            noOrderExistsMessage();
        }
    }
    
    //Option 5: Remove an order
    private void removeOrder() throws FlooringMasteryPersistenceException {
        LocalDate dateInput = view.getOrderDateInputOption5();
        String customerNameInput = view.getCustomerNameInputOption5();
        Order orderToRemove = service.getOrderByNameAndDate(customerNameInput, dateInput);
        String confirm = "";
        
        //Get highest order number from memory
        int highestOrderNum = service.returnHighestOrderNumber();
        
        //Make sure that orderToRemove is a real order from memory
        //If so, go ahead and remove it
            //Then check if user wants to remove it
            //If so, remove it
            //If not, don't
        //If not, don't do anything with it
        if (orderToRemove.getOrderNumber() != -1) {
            confirm = view.removeOrder(orderToRemove);
            if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                service.removeOrder(orderToRemove.getOrderNumber());
                
                //Assign "removedOrderNumWasHighest" in DAO only if the removed
                //Order was the highest in the system
                if (orderToRemove.getOrderNumber() == highestOrderNum) {
                    service.assignRemovedOrderNumWasHighest(highestOrderNum);
                }
            }
        } else {
            noOrderExistsMessage();
        }
    }
    
    //Option 6: Save current work
    private void saveCurrentWork(int mode) throws FlooringMasteryPersistenceException {
        service.writeFileData(mode);
        view.savedCurrentWorkMessage();
    }
    
    //Load Order, Product, and Tax info from files
    private void loadFileData(int mode) throws FlooringMasteryPersistenceException {
        service.loadFileData(mode);
    }
    
    //Write Order data from memory to file
    private void writeFileData(int mode) throws FlooringMasteryPersistenceException {
        service.writeFileData(mode);
    }
    
    private void noOrderExistsMessage() {
        view.noOrderExistsMessage();
    }
    
    //Other messages to print if needed
    private void unknownCommand() {
        view.displayUnknownCommandMessage();
    }
    
    private void exitMessage() {
        view.displayExitMessage();
    }  
}
