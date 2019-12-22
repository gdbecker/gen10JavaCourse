package gdb.vendingmachine.service;

import gdb.vendingmachine.dao.Coin;
import gdb.vendingmachine.dao.VendingMachinePersistenceException;
import gdb.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

public interface VendingMachineService {
    
    //Matches up with coresponding DAO method
    List<Item> getAvailableVendingMachineItems() 
            throws VendingMachinePersistenceException;
    
    //Matches up with coresponding DAO method
    List<Item> getAllVendingMachineItems() 
            throws VendingMachinePersistenceException;
    
    //Matches up with coresponding DAO method
    Item getItem(String itemName)
            throws VendingMachinePersistenceException;
    
    //Matches up with coresponding DAO method
    Map<Coin, BigDecimal> getChange(BigDecimal userInput, BigDecimal itemPrice) 
            throws VendingMachinePersistenceException;
    
    //Matches up with coresponding DAO method
    void updateInventory(Item item) 
            throws VendingMachinePersistenceException;
    
    //Matches up with coresponding Audit DAO method
    void writeAuditLog(String entry, int option) 
            throws VendingMachinePersistenceException;

    //Matches up with coresponding DAO method
    public void loadVendingMachine()
            throws VendingMachinePersistenceException;

    //Matches up with coresponding DAO method
    public void writeVendingMachine()
            throws VendingMachinePersistenceException;
        
    //Matches up with coresponding DAO method    
    public Item removeItem(String itemName) 
            throws VendingMachinePersistenceException;
    
    //Matches up with coresponding DAO method
    public Item addItem(Item item) 
            throws VendingMachinePersistenceException;
    
    //Grabbing the item from memory and making sure that its inventory is not 0
    //Otherwise, throw exception
    public boolean validateItem(Item item)
            throws VendingMachinePersistenceException,
            VendingMachineNoItemInventoryException;
}
