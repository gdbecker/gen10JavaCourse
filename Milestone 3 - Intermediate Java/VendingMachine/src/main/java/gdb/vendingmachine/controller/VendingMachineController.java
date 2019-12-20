package gdb.vendingmachine.controller;

import gdb.vendingmachine.dao.Coin;
import gdb.vendingmachine.dao.VendingMachinePersistenceException;
import gdb.vendingmachine.dto.Item;
import gdb.vendingmachine.service.VendingMachineService;
import gdb.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
        //Load vending machine data into the system
        try {
            loadVendingMachine(); 
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        //Welcome user to vending machine
        view.displayWelcomeMessage();
        
        //Instantiate key variables to control staying in program
        boolean keepGoing = true;
        int initialSelection = 0;
        int menuSelection = 0;
        int numAvailItems = 0;
        String change = "";
        
        try {
            while (keepGoing) {
                initialSelection = showInitialMachineGetSelection();
                
                switch(initialSelection) {
                    case 1:   
                        
                        BigDecimal userMoney = getMoney();
                                               
                        numAvailItems = getAndShowAvailableItems();
                        menuSelection = getSelectionFromItems(numAvailItems);
                        Item selectedItem = getItemFromSelection(menuSelection);
                        
                        BigDecimal itemPrice = selectedItem.getPrice();
                        
                        //Decision tree for getting change
                        if (userMoney.compareTo(itemPrice) == 1) {
                            
                            getChange(userMoney, itemPrice); //show amount of change
                            
                            
                            updateInventory(selectedItem); //update inventory in memory
                            writeAuditLog(selectedItem.getName(), 1); //write to audit log
                        } 
                        
                        else if (userMoney.compareTo(itemPrice) == 0) {
                            
                            getChange(userMoney, itemPrice); //show amount of change
                            
                            
                            updateInventory(selectedItem); //update inventory in memory
                            writeAuditLog(selectedItem.getName(), 1); //write to audit log
                        }
                        
                        else {
                            insufficientFundsError(userMoney, itemPrice);
                            writeAuditLog(selectedItem.getName(), 2); //write to audit log
                        }
                        
                        break;
                    case 2:
                        keepGoing = false;
                        break;
                    default:
                        keepGoing = false;
                        unknownCommand();
                }
            }
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        //Write vending machine data from memory back to the file
        try {
            writeVendingMachine(); 
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        exitMessage();
        
    }
    
    //Get and show available vending machine items
    //Return selection made by user (input money or leave)
    private int showInitialMachineGetSelection() throws VendingMachinePersistenceException {
        List<Item> availItemList = service.getAvailableVendingMachineItems();
        return view.displayAvailableVendingMachineItemsNoIndex(availItemList);
    }
            
    //Get and show available vending machine items
    //Return the number of available items in machine
    private int getAndShowAvailableItems() throws VendingMachinePersistenceException {
        List<Item> availItemList = service.getAvailableVendingMachineItems();
        return view.displayAvailableVendingMachineItemsWithIndex(availItemList);
    }
    
    //Get money input from the user
    private BigDecimal getMoney() {
        return view.getMoney();
    }
    
    private void getChange(BigDecimal userInput, BigDecimal itemPrice) throws VendingMachinePersistenceException {
        Map<Coin, BigDecimal> coins = service.getChange(userInput, itemPrice);
        view.displayChangeAmount(coins);
    }
    
    private Item getItemFromSelection(int selection) throws VendingMachinePersistenceException {
        List<Item> itemList = service.getAvailableVendingMachineItems();
        Item selectedItem = new Item();
        
        int counter = 1;
        for (Item i : itemList) {
            String name = i.getName();
            if (counter == selection) {
                selectedItem = i;
                break;
            }
            counter++;
        }
        return selectedItem;
    }
    
    //Print out item selection menu and get input
    private int getSelectionFromItems(int numAvailItems) throws VendingMachinePersistenceException {
        return view.getSelectionFromItems(numAvailItems);
    }
    
    //Show insufficient funds error
    //inform user how much money they still need to input
    private void insufficientFundsError(BigDecimal userMoney, BigDecimal itemPrice) {
        view.displayInsufficientFundsMessage(userMoney, itemPrice);
    }
    
    //Update inventory amount in DAO memory
    private void updateInventory(Item item) throws VendingMachinePersistenceException {
        service.updateInventory(item);
    }
    
    //Write log entry to the file when inventory is updated
    private void writeAuditLog(String itemName, int option) throws VendingMachinePersistenceException {
        service.writeAuditLog(itemName, option);
    }
    
    //Load vending machine data into the system from file
    private void loadVendingMachine() throws VendingMachinePersistenceException {
        service.loadVendingMachine();
    }
    
    //Write vending machine data from system to file
    private void writeVendingMachine() throws VendingMachinePersistenceException {
        service.writeVendingMachine();
    }
    
    //Other messages to print if needed
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }  
}
