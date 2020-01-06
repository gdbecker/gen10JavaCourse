package gdb.vendingmachine.controller;

import gdb.vendingmachine.dao.Coin;
import gdb.vendingmachine.dao.VendingMachinePersistenceException;
import gdb.vendingmachine.dto.Item;
import gdb.vendingmachine.service.VendingMachineNoItemInventoryException;
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
        String isValid = "";
        int initialSelection = 0;
        int menuSelection = 0;
        int numAvailItems = 0;
        
        try {
            while (keepGoing) {
                initialSelection = showInitialMachineGetSelection();
                
                switch(initialSelection) {
                    case 1:   
                        
                        BigDecimal userMoney = getMoney();
                        
                        //Write to the audit log if user input was invalid (should return as 0)
                        //and then exit the program
                        BigDecimal blank = new BigDecimal(0); //exists just for comparison
                        if (userMoney.equals(blank)) {
                            writeAuditLog(userMoney.toString(), 3); //write to audit log
                            break;
                        }
                        
                        //Validate userMoney to make sure it's not negative or 
                        //greater than the limit of $50
                        isValid = catchBadUserInputMoney(userMoney);
                        if (!isValid.equals("Good")) {
                            printMessage(isValid);
                            break;
                        }
                          
                        //Show available items to screen and return the number of
                        //items available (this makes it customizable to whatever
                        //number of items are available in the machine
                        numAvailItems = getAndShowAvailableItems();
                        
                        //Return the selection made from the user
                        menuSelection = getSelectionFromItems(numAvailItems);
                        
                        //Grab the item selected from the menu
                        Item selectedItem = getItemFromSelection(menuSelection);
                        
                        //Validate the item slected to make sure that its inventory
                        //is greater than 0
                        try {
                            validateItem(selectedItem);
                        } catch(VendingMachineNoItemInventoryException e) {
                            view.displayErrorMessage(e.getMessage());
                            break;
                        }
                        
                        BigDecimal itemPrice = selectedItem.getPrice();
                        
                        //Decision tree for getting change
                        //Case for if userMoney > itemPrice
                        if (userMoney.compareTo(itemPrice) == 1) {
                            //Show amount of change
                            getChangeTransactionInfo(userMoney, selectedItem);
                            
                            //Update inventory in memory
                            updateInventory(selectedItem);
                            
                            //Write to audit log
                            writeAuditLog(selectedItem.getName(), 1);
                        } 
                        
                        //Case for if userMoney == itemPrice
                        else if (userMoney.compareTo(itemPrice) == 0) {
                            //Show amount of change
                            getChangeTransactionInfo(userMoney, selectedItem);
                            
                            //Update inventory in memory
                            updateInventory(selectedItem);
                            
                            //Write to audit log
                            writeAuditLog(selectedItem.getName(), 1);
                        }
                        
                        //Case for if userMoney < itemPrice
                        else {
                            //Show error for insufficient funds
                            //Essentially catching an exception for not putting
                            //in enough money
                            insufficientFundsError(userMoney, selectedItem);
                            
                            //Write to audit log
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
    
    //Validate the money input from the user
    private String catchBadUserInputMoney(BigDecimal userInput) {
        return view.catchBadUserInputMoney(userInput);
    }
    
    //Takes in amount of money user put in and the Item object for what they bought
    //Prints out transaction information and change info in coin amounts
    private void getChangeTransactionInfo(BigDecimal userInput, Item itemBought) throws VendingMachinePersistenceException {
        Map<Coin, BigDecimal> coins = service.getChange(userInput, itemBought.getPrice());
        view.displayChangeTransactionInfo(userInput, itemBought, coins);
    }
    
    //Finds the specific Item that the user selected from number input
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
    private void insufficientFundsError(BigDecimal userMoney, Item selectedItem) {
        view.displayInsufficientFundsMessage(userMoney, selectedItem);
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
    
    //Validate the Item
    //Throw exception if the one selected does not have inventory greater than 0
    private void validateItem(Item item) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        boolean isValidated = service.validateItem(item);
    }
    
    //Print a message out to the console
    private void printMessage(String message) {
        view.printMessage(message);
    }
    
    //Other messages to print if needed
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }  
}