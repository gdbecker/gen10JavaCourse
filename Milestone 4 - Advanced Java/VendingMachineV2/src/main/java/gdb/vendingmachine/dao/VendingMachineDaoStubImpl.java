package gdb.vendingmachine.dao;

import gdb.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

//This is basically a dumbed down version of the VendingMachineDaoFileImpl file
//This exists for the purpose of testing that these methods work
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    Item onlyItem;
    Map<String, Item> itemMap = new HashMap<>();
    
    //Create a default constructor with info filled in so that there's data
    //for the service test to use
    public VendingMachineDaoStubImpl() {
        onlyItem = new Item();
        onlyItem.setName("Dasani");
        onlyItem.setPrice(BigDecimal.TEN);
        onlyItem.setInventory(10);
        
        itemMap.put(onlyItem.getName(), onlyItem);
    }

    @Override
    public List<Item> getAvailableVendingMachineItems() throws VendingMachinePersistenceException {
        return itemMap.values()
                .stream()
                .filter(i -> i.getInventory() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getAllVendingMachineItems() throws VendingMachinePersistenceException {
        return itemMap.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException {
        //We don't care what this method does since this is being used to test the 
        //service layer.
        //Ensure that the Item object gets returned
        if (itemName.equals(onlyItem.getName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public Map<Coin, BigDecimal> getChange(BigDecimal userInput, BigDecimal itemPrice) throws VendingMachinePersistenceException {
        BigDecimal diff = userInput.subtract(itemPrice);

        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickel = new BigDecimal("0.05");
        BigDecimal penny = new BigDecimal("0.01");
        
        BigDecimal numQuarters = diff.divide(quarter, 0, RoundingMode.DOWN);
        diff = diff.subtract((numQuarters).multiply(quarter));
        
        BigDecimal numDimes = diff.divide(dime, 0, RoundingMode.DOWN);
        diff = diff.subtract((numDimes).multiply(dime));
        
        BigDecimal numNickels = diff.divide(nickel, 0, RoundingMode.DOWN);
        diff = diff.subtract((numNickels).multiply(nickel));
        
        BigDecimal numPennies = diff.divide(penny, 0, RoundingMode.DOWN);
        diff = diff.subtract((numPennies).multiply(penny));
        
        Map<Coin, BigDecimal> coins = new HashMap<>();
        Coin coinType = Coin.QUARTER;
        coins.put(coinType.QUARTER, numQuarters);
        coins.put(coinType.DIME, numDimes);
        coins.put(coinType.NICKEL, numNickels);
        coins.put(coinType.PENNY, numPennies);
        
        return coins;
    }

    @Override
    public void updateInventory(Item item) throws VendingMachinePersistenceException {
        //No matter what Item gets thrown in here, only work with onlyItem
        int inv = onlyItem.getInventory();
        inv--;
        onlyItem.setInventory(inv);
    }

    @Override
    public void loadVendingMachine() throws VendingMachinePersistenceException {
        //Do nothing
    }

    @Override
    public void writeVendingMachine() throws VendingMachinePersistenceException {
        //Do nothing
    }

    @Override
    public Item removeItem(String itemName) throws VendingMachinePersistenceException {
        //We don't care what this method does since this is being used to test the 
        //service layer.
        //Ensure that the Item object gets returned
        if (itemName.equals(onlyItem.getName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public Item addItem(Item item) throws VendingMachinePersistenceException {
        //Add Item to the Map
        itemMap.put(item.getName(), item);
        
        return item;
    }
}
