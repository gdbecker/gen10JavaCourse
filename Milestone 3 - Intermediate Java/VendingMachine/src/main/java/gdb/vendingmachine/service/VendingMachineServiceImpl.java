package gdb.vendingmachine.service;

import gdb.vendingmachine.dao.Coin;
import gdb.vendingmachine.dao.VendingMachineAuditDao;
import gdb.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import gdb.vendingmachine.dao.VendingMachineDao;
import gdb.vendingmachine.dao.VendingMachineDaoFileImpl;
import gdb.vendingmachine.dao.VendingMachinePersistenceException;
import gdb.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

public class VendingMachineServiceImpl implements VendingMachineService {

    VendingMachineDao mainDao = new VendingMachineDaoFileImpl();
    VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
    
    //Create Constructor - needs to contain both DAO objects
    public VendingMachineServiceImpl(VendingMachineDao mainDao, VendingMachineAuditDao auditDao) {
        this.mainDao = mainDao;
        this.auditDao = auditDao;
    }
    
    @Override
    public List<Item> getAvailableVendingMachineItems() throws VendingMachinePersistenceException {
        return mainDao.getAvailableVendingMachineItems();
    }

    @Override
    public List<Item> getAllVendingMachineItems() throws VendingMachinePersistenceException {
        return mainDao.getAllVendingMachineItems();
    }

    @Override
    public Map<Coin, BigDecimal> getChange(BigDecimal userInput, BigDecimal itemPrice) throws VendingMachinePersistenceException {
        return mainDao.getChange(userInput, itemPrice);
    }

    @Override
    public void updateInventory(Item item) throws VendingMachinePersistenceException {
        mainDao.updateInventory(item);
    }

    @Override
    public void loadVendingMachine() throws VendingMachinePersistenceException {
        mainDao.loadVendingMachine();
    }

    @Override
    public void writeVendingMachine() throws VendingMachinePersistenceException {
        mainDao.writeVendingMachine();
    }
    
    @Override
    public void writeAuditLog(String itemName, int option) throws VendingMachinePersistenceException {
        Item affectedItem = new Item();
        affectedItem = mainDao.getItem(itemName);
        
        //Write to the audit log, depending on the option
        switch (option) {
            case 1: 
                auditDao.writeAuditEntry(affectedItem.getName() + " has been vended. Inventory is now " + affectedItem.getInventory());
                break;
            case 2: 
                auditDao.writeAuditEntry(affectedItem.getName() + " has a vending attempt, but unsuccessful. User did not input enough money. Inventory is still " + affectedItem.getInventory());
                break;
            default:
                break;
        }
    }
}
