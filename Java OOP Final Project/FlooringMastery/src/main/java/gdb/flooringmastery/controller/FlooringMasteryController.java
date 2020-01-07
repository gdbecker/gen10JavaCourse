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
            do {
                mode = displayModeMenuGetSelection();
            } while (mode != 1 && mode != 2 && mode != 3);    
            
            switch(mode) {
                case 1: //Production Mode
                    loadFileData(mode);
                    break;
                case 2: //Training Mode
                    loadFileData(mode);
                    break;
                case 3: //Exit early
                    exitMessage();
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
                    keepGoing = false;
                    break;
            }
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        //Main structure to keep program looping through
        try {
            while (keepGoing) {
                menuSelection = displayMainMenuGetSelection(mode);
                
                switch(menuSelection) {
                    case 1:   
                        displayAllOrders(mode);
                        break;
                    case 2:   
                        displayOrdersByDate(mode);
                        break;
                    case 3:
                        addNewOrder(mode);
                        break;
                    case 4:
                        editOrder(mode);
                        break;
                    case 5:
                        removeOrder(mode);
                        break;
                    case 6:
                        saveCurrentWork(mode);
                        break;
                    case 7:
                        exitMessage();
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
        
        //Write Order data from memory back to the file, only in Production mode
        try {
            switch(mode) {
                case 1: //Production Mode
                    writeFileData(mode);
                    break;
                case 2: //Training Mode
                    //Do nothing
                    break;
                case 3: //Exit early
                    //Do nothing
                    break;
                default:
                    unknownCommand();
            }
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    public int displayModeMenuGetSelection() {
        return view.displayModeMenuGetSelection();
    }
    
    public int displayMainMenuGetSelection(int mode) {
        return view.displayMainMenuGetSelection(mode);
    }
    
    //Option 1: Display all orders
    private void displayAllOrders(int mode) throws FlooringMasteryPersistenceException {
        List<Order> orderList = service.getAllOrders();
        view.displayOrders(orderList);
        writeAuditLog(1, mode);
    }
    
    //Option 2: Display orders by date input
    private void displayOrdersByDate(int mode) throws FlooringMasteryPersistenceException {
        LocalDate dateInput = view.getOrderDateInputOption2();
        List<Order> orderList = service.getOrdersByDate(dateInput);
        view.displayOrders(orderList);
        writeAuditLog(dateInput, mode);
    }
    
    //Option 3: Add an order
    private void addNewOrder(int mode) throws FlooringMasteryPersistenceException {
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
            writeAuditLog(updatedNewOrder, 3, mode);
        } else {
            //Don't do anything with this uncommited order
        }        
    }
    
    //Option 4: Edit an order
    private void editOrder(int mode) throws FlooringMasteryPersistenceException {
        int orderNumInput = view.getOrderNumberInputOption4();
        List<Product> productList = service.getAllProducts();
        List<Tax> taxList = service.getAllTaxes();
        Order orderToEdit = service.getOrderByOrderNumber(orderNumInput);
        
        //Make sure that orderToEdit is a real order from memory
        //If so, go ahead and edit it
        //If not, don't do anything with it
        try {
            Order editedOrder = view.editOrder(orderToEdit, productList, taxList);
            Order updatedEditedOrder = service.fillRemainingOrderDetailsFromAddOrder(editedOrder);
            service.removeOrder(orderToEdit.getOrderNumber());
            service.addOrder(updatedEditedOrder);
            writeAuditLog(updatedEditedOrder, 4, mode);
        } catch (NullPointerException e) {
            noOrderExistsMessage();
        }
    }
    
    //Option 5: Remove an order
    private void removeOrder(int mode) throws FlooringMasteryPersistenceException {
        int orderNumInput = view.getOrderNumberInputOption5();
        Order orderToRemove = service.getOrderByOrderNumber(orderNumInput);
        String confirm = "";
        
        //Get highest order number from memory
        int highestOrderNum = service.returnHighestOrderNumber();
        
        //Make sure that orderToRemove is a real order from memory
        //If so, go ahead and remove it
            //Then check if user wants to remove it
            //If so, remove it
            //If not, don't
        //If not, don't do anything with it
        try {
            confirm = view.removeOrder(orderToRemove);
            if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                service.removeOrder(orderToRemove.getOrderNumber());
                writeAuditLog(orderToRemove, 5, mode);
                
                //Assign "removedOrderNumWasHighest" in DAO only if the removed
                //Order was the highest in the system
                if (orderToRemove.getOrderNumber() == highestOrderNum) {
                    service.assignRemovedOrderNumWasHighest(highestOrderNum);
                }
            }
        } catch (NullPointerException e) {
            noOrderExistsMessage();
        }
    }
    
    //Option 6: Save current work
    private void saveCurrentWork(int mode) throws FlooringMasteryPersistenceException {
        service.writeFileData(mode);
        view.savedCurrentWorkMessage(mode);
        service.writeAuditLog(6, mode);
    }
    
    //Audit Log methods
    //For option 1: View all Orders
    private void writeAuditLog(int option, int mode) throws FlooringMasteryPersistenceException {
        service.writeAuditLog(option, mode);
    }
    
    //For option 2: Display Orders by date input
    private void writeAuditLog(LocalDate date, int mode) throws FlooringMasteryPersistenceException {
        service.writeAuditLog(date, mode);
    }
    
    //For options 3-5: adding, editing, and removing Orders
    private void writeAuditLog(Order order, int option, int mode) throws FlooringMasteryPersistenceException {
        service.writeAuditLog(order, option, mode);
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
