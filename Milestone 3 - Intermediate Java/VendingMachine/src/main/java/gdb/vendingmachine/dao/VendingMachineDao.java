package gdb.vendingmachine.dao;

import gdb.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

public interface VendingMachineDao {
    
    /**
     * Main function: get all currently available items in the inventory to 
     * display to user (i.e. Only Item objects with inventory > 0)
     * 
     * @param None
     * 
     * @return List<Item>: list of Item objects currently available in the machine
     */
    List<Item> getAvailableVendingMachineItems() throws VendingMachinePersistenceException;
    
    /**
     * Main function: get all Items in the machine (doesn't matter what value 
     * inventory is)
     * 
     * @param None
     * 
     * @return List<Item>: list of all Item objects in memory
     */
    List<Item> getAllVendingMachineItems() throws VendingMachinePersistenceException;
    
    /**
     * Main function: get specific Item within machine
     * 
     * @param itemName: a String containing name of Item
     * 
     * @return Item: an Item with itemName as its name property
     */
    Item getItem(String itemName) throws VendingMachinePersistenceException;
            
    /**
     * Main function: output change if user 
     * 
     * @param userInput: BigDecimal value of money user gave machine
     * @param itemPrice: BigDecimal value of cost of item
     * 
     * @return String containing amount of quarters, dimes, nickels, and pennies
     * given to the user
     */
    Map<Coin, BigDecimal> getChange(BigDecimal userInput, BigDecimal itemPrice) 
            throws VendingMachinePersistenceException;
    
    /**
     * Main function: update inventory in memory for item taken out 
     * 
     * @param item: Item object  
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
