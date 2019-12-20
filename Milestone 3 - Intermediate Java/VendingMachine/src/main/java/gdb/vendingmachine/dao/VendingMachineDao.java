package gdb.vendingmachine.dao;

import gdb.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

public interface VendingMachineDao {
    
    /**
     * Main function: get all currently available items in the inventory to 
     * display to user
     * 
     * @param List<Item> list of Item objects currently available in the machine
     * 
     * @return nothing to return
     */
    void getVendingMachineItems(List<Item> itemList) throws VendingMachinePersistenceException;
    
    /**
     * Main function: output change if user 
     * 
     * @param userInput BigDecimal value of money user gave machine
     * @param itemPrice BigDecimal value of cost of item
     * 
     * @return String containing amount of quarters, dimes, nickels, and pennies
     * given to the user
     */
    String getChange(BigDecimal userInput, BigDecimal itemPrice) throws VendingMachinePersistenceException;
    
    /**
     * Main function: update inventory in memory for item taken out 
     * 
     * @param item Item object  
     * 
     * @return nothing to return
     */
    void updateInventory(Item item) throws VendingMachinePersistenceException;
    
    /**
     * Main function: import file containing vending machine items
     * 
     * Memory will be updated with all Items of vending machine
     */
    void loadVendingMachine() throws VendingMachinePersistenceException;
    
    /**
     * Main function: write to file containing vending machine items
     * 
     * Vending machine Items will be added from memory to file
     */
    void writeVendingMachine() throws VendingMachinePersistenceException;
    
}
