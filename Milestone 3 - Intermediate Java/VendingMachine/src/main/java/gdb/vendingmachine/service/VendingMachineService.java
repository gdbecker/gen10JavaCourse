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
    
    Map<Coin, BigDecimal> getChange(BigDecimal userInput, BigDecimal itemPrice) 
            throws VendingMachinePersistenceException;
    
    void updateInventory(Item item) 
            throws VendingMachinePersistenceException;
   
    void loadVendingMachine() 
            throws VendingMachinePersistenceException;
    
    void writeVendingMachine() 
            throws VendingMachinePersistenceException;
    
    void writeAuditLog(String entry, int option) 
            throws VendingMachinePersistenceException;
}
