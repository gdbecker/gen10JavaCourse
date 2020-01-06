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
        String userMoney = io.readString("How much money would you like to put in? Limit: $50 (format: XX.XX)");
        BigDecimal money = new BigDecimal(0);
        
        try {
            money = new BigDecimal(userMoney).setScale(2, RoundingMode.HALF_UP);
        } catch (NumberFormatException e) {
            io.print("ERROR: Invalid input!");
        }
        
        return money;
    }
    
    //Check for potential errors for user money input:
    //1: negative input
    //2: inputting greater than the limit of $50
    public String catchBadUserInputMoney(BigDecimal userInput) {
        //BigDecimal values used for comparison
        BigDecimal zero = new BigDecimal("0");
        BigDecimal fifty = new BigDecimal("50");
        String toPrint = "";
        
        //If userInput > 50 (greater than the machine limit) ||
        //If userInput < 0 (negative input) ||
        //If userInput = 0 (can't put in nothing to the machine)
        if (userInput.compareTo(fifty) == 1) {
            toPrint = "Invalid input! You inputted too much money. Machine limit is $50.";
        } else if (userInput.compareTo(zero) == -1) {
            toPrint =  "Invalid input! You tried to input a negative amount."; 
        } else if (userInput.compareTo(zero) == 0) {
            toPrint =  "Invalid input! Input an amount greater than $0 to continue.";
        } else {
            toPrint = "Good";
        }
        
        return toPrint;
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
    public void displayInsufficientFundsMessage(BigDecimal userMoney, Item selectedItem) {
        io.print("You didn't input enough money for " + selectedItem.getName() + "!");
        BigDecimal difference = selectedItem.getPrice().subtract(userMoney);
        io.print("The price for " + selectedItem.getName() + " is $" + selectedItem.getPrice() + " and you inputted $" + userMoney + ".");
        io.print("You were short by $" + difference + ".");
        io.print("Returning your money back to you.");
        io.print("");
    }
    
    //Display message about change given to the user
    public void displayChangeTransactionInfo(BigDecimal userMoney, Item itemBought, Map<Coin, BigDecimal> coins) {
        Coin coinType = null;
        io.print("You bought " + itemBought.getName() + " which cost $" + itemBought.getPrice() + ".");
        io.print("You put $" + userMoney + " into the machine.");
        io.print("Your change is $" + userMoney.subtract(itemBought.getPrice()) + ".");
        io.print("");
        io.print("Your change ---");
        io.print("Quarters: " + coins.get(coinType.QUARTER));
        io.print("Dimes: " + coins.get(coinType.DIME));
        io.print("Nickels: " + coins.get(coinType.NICKEL));
        io.print("Pennies: " + coins.get(coinType.PENNY));
        io.print("");
    }
    
    //Print anything to console
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
