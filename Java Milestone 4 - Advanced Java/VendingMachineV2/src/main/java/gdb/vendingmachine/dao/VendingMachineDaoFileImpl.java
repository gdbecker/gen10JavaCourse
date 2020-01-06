package gdb.vendingmachine.dao;

import gdb.vendingmachine.dto.Item;
import gdb.vendingmachine.service.VendingMachineInsufficientFundsException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

public class VendingMachineDaoFileImpl implements VendingMachineDao {
    
    Map<String, Item> vendingMachine = new HashMap<>();
    public static final String FILE = "VendingMachine.txt";
    public static final String DELIMITER = "::";

    @Override
    public List<Item> getAvailableVendingMachineItems() throws VendingMachinePersistenceException {
        //Example of using a lambda within this project
        return vendingMachine.values()
                .stream()
                .filter(i -> i.getInventory() > 0)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Item> getAllVendingMachineItems() throws VendingMachinePersistenceException {
        return new ArrayList<Item>(vendingMachine.values());
    }

    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException {
        return vendingMachine.get(itemName);
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
        int inv = item.getInventory();
        inv--;
        item.setInventory(inv);
    }

    //Part of loading from Vending Machine file (before menu pops up)
    private Item unmarshallItem(String itemAsText){
        //itemAsText is expecting a line read in from our file.
        String[] itemTokens = itemAsText.split(DELIMITER);

        //Create new Item object and add properties to it from the file
        Item itemFromFile = new Item();
        itemFromFile.setName(itemTokens[0]);
        
        String priceFromFile = itemTokens[1];
        BigDecimal price = new BigDecimal(priceFromFile);
        itemFromFile.setPrice(price);
        
        itemFromFile.setInventory(Integer.parseInt(itemTokens[2]));
        
        //Return new Item object
        return itemFromFile;
    }
    
    @Override
    public void loadVendingMachine() throws VendingMachinePersistenceException {
        Scanner sc;
        
        try {
            //Create Scanner for reading the file
            sc = new Scanner(new BufferedReader(new FileReader(FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("-_- Could not load Item data into memory.", e);
        }
        
        //currentLine holds the most recent line read from the file
        String currentLine;
        
        //currentMP3 holds the most recent address unmarshalled
        Item currentItem;
        
        //Go through the FILE line by line, decoding each line into an 
        //Item object by calling the unmarshallItem method.
        //Process while we have more lines in the file
        while (sc.hasNextLine()) {
            //Get the next line in the file
            currentLine = sc.nextLine();
            
            //Unmarshall the line into an Item
            currentItem = unmarshallItem(currentLine);
            
            //Putting all Item objects into the vendingMachine Map in memory
            vendingMachine.put(currentItem.getName(), currentItem);
        }
    }

    //Part of writing memory to file (after program completes)
    private String marshallItem(Item item) {
        //Turning an Item object into a String to be printed into the file
        
        //Add each of the properties from the Item to the String
        String itemAsText = item.getName()+ DELIMITER;
        itemAsText += item.getPrice()+ DELIMITER;
        itemAsText += item.getInventory();

        return itemAsText;
    }
    
    @Override
    public void writeVendingMachine() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save data.", e);
        }

        //Write out Items objects to the file
        String itemAsText;
        List<Item> itemList = this.getAllVendingMachineItems(); //getting all Item objects from Map in memory
        for (Item currentItem : itemList) {
            //Make an Item object into a String using the above method
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush(); //Making PrintWriter go to the next line
        }
        
        out.close();
    }

    @Override
    public Item removeItem(String itemName) throws VendingMachinePersistenceException {
        return vendingMachine.remove(itemName);
    }

    @Override
    public Item addItem(Item item) throws VendingMachinePersistenceException {
        return vendingMachine.put(item.getName(), item);
    }
    
}
