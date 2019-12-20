package gdb.vendingmachine.ui;

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
        
    }
    
    //Display currently available items in vending machine
    
    //Ask user to input an amount of money into the machine
    //(Must do before a selection can be made for an item)
    
    //Show the main menu for the vending machine and get initial selection from user
    public void showMenuGetSelection() {
    }
    
    //Display message about insufficient funds
    
    //Display message about change given to the user
    
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
