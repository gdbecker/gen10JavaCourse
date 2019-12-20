package gdb.vendingmachine.ui;

import gdb.vendingmachine.dao.Coin;
import gdb.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

public class VendingMachineView {
    
    private UserIO io = new UserIOConsoleImpl();
    
    //Constructor for making a new View object
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    //Display welcome message to the vending machine
    public void displayWelcomeMessage() {
        io.print("<><><>< SNACK VENDING MACHINE ><><><>");
        io.print("");
    }
    
    //Display currently available items in vending machine
    //Return the number of items availabnle in the machine so that showMenuGetSelection
    //can control the number options the user has to choose from
    public int displayAvailableVendingMachineItemsWithIndex(List<Item> itemList) {
        int counter = 1;
        
        io.print("");
        
        for (Item i : itemList) {
            io.print("(" + counter + ") " + i.getName() + ", Price: $" + i.getPrice());
            counter++;
        }
        
        return counter - 1;
    }
    
    //Display currently available items in vending machine
    //Don't print indexes with these, just print info and allow user to make slection
    //if they want to input money or exit
    public int displayAvailableVendingMachineItemsNoIndex(List<Item> itemList) {
        int selection = 0;
        
        io.print("<>< Here's what's available ><>");
        io.print("");
        
        for (Item i : itemList) {
            io.print(i.getName() + ", Price: $" + i.getPrice());
        }
        
        io.print("");
        io.print("(1) Insert money to vend an item");
        io.print("(2) Exit");
        io.print("");

        try {
            selection = io.readInt("Please make your selection from the menu above: ", 1, 2);
        } catch (InputMismatchException e) {
            io.print("Bad input! Exiting program.");
        }
        
        return selection;
    }
    
    //Ask user to input an amount of money into the machine
    //(Must do before a selection can be made for an item)
    public BigDecimal getMoney() {
        String userMoney = io.readString("How much money would you like to put in? (format: XX.XX)");
        BigDecimal money = new BigDecimal(0);
        
        try {
            money = new BigDecimal(userMoney).setScale(2, RoundingMode.HALF_UP);
        } catch (NumberFormatException e) {
            io.print("ERROR: Invalid inuput!");
        }
        
        return money;
    }
    
    //Show the main menu for the vending machine and get initial selection from user
    public int getSelectionFromItems(int counter) {
        io.print("");
        int selection = 0;
        
        try {
            selection = io.readInt("Make your selection from the list above from options 1 to " + counter, 1, counter);
        } catch (InputMismatchException e) {
            io.print("Bad input! Exiting program.");
        }
        
        return selection;
    }
    
    //Display message about insufficient funds
    public void displayInsufficientFundsMessage(BigDecimal userMoney, BigDecimal itemPrice) {
        io.print("You didn't input enough money for this item!");
        BigDecimal difference = itemPrice.subtract(userMoney);
        io.print("Item price is $" + itemPrice + " and you inputted $" + userMoney);
        io.print("You still need to input $" + difference);
        io.print("");
    }
    
    //Display message about change given to the user
    public void displayChangeAmount(Map<Coin, BigDecimal> coins) {
        Coin coinType = Coin.QUARTER;
        io.print("");
        io.print("Your change ---");
        io.print("Quarters: " + coins.get(coinType.QUARTER));
        io.print("Dimes: " + coins.get(coinType.DIME));
        io.print("Nickels: " + coins.get(coinType.NICKEL));
        io.print("Pennies: " + coins.get(coinType.PENNY));
        io.print("");
    }
    
    public void printMessage(String message) {
        io.print(message);
    }
    
    public void displayExitBanner() {
        io.print("");
        io.print("Good bye!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
