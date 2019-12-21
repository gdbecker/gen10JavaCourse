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
    
    List<Item> getAvailableVendingMachineItems() 
            throws VendingMachinePersistenceException;
    
    List<Item> getAllVendingMachineItems() 
            throws VendingMachinePersistenceException;
    
    Item getItem(String itemName)
            throws VendingMachinePersistenceException;
    
    Map<Coin, BigDecimal> getChange(BigDecimal userInput, BigDecimal itemPrice) 
            throws VendingMachinePersistenceException;
    
    void updateInventory(Item item) 
            throws VendingMachinePersistenceException;
    
    void writeAuditLog(String entry, int option) 
            throws VendingMachinePersistenceException;

    public void loadVendingMachine()
            throws VendingMachinePersistenceException;

    public void writeVendingMachine()
            throws VendingMachinePersistenceException;
            
    public Item removeItem(String itemName) 
            throws VendingMachinePersistenceException;
    
    public Item addItem(Item item) 
            throws VendingMachinePersistenceException;
}
