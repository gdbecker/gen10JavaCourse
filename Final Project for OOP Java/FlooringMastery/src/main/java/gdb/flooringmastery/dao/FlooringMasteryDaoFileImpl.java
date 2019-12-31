package gdb.flooringmastery.dao;

import gdb.flooringmastery.dto.Order;
import gdb.flooringmastery.dto.Product;
import gdb.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Final Project OOP
 * @author garrettbecker
 */

public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {
    //Create Maps for each type of data being inputted into memory
    Map<Integer, Order> orderData = new HashMap<>(); //Key: orderNumber
    Map<String, Product> productData = new HashMap<>(); //Key: productType
    Map<String, Tax> taxData = new HashMap<>(); //Key: state
    
    //Any other working variables
    int removedOrderNumWasHighest = 0; //So system won't assign order number the next highest if that one was just removed
    
    //Create final variables for each file to load and write to
    public static final String FILE_ORDER = "OrderData.txt";
    public static final String FILE_ORDER_TRAINING = "TrainingOrderData.txt";
    public static final String FILE_PRODUCT = "ProductData.txt";
    public static final String FILE_TAX = "TaxData.txt";
    public static final String DELIMITER = "::";

    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException {
        return new ArrayList<Order>(orderData.values());
    }
    
    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        return new ArrayList<Product>(productData.values());
    }
    
    @Override
    public List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
        return new ArrayList<Tax>(taxData.values());
    }
    
    @Override
    public List<Order> getOrdersByDate(LocalDate orderDate) throws FlooringMasteryPersistenceException {
        //Example of using a lambda and stream with this project
        return orderData.values()
                .stream()
                .filter(o -> o.getOrderDate().equals(orderDate))
                .collect(Collectors.toList());
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {
        return orderData.put(order.getOrderNumber(), order);
    }

    @Override
    public Order removeOrder(int orderNumber) throws FlooringMasteryPersistenceException {
        return orderData.remove(orderNumber);
    }

    @Override
    public Order getOrderByOrderNumber(int orderNumber) throws FlooringMasteryPersistenceException {
        return orderData.get(orderNumber);
    }
    
    @Override
    public Order getOrderByNameAndDate(String customerName, LocalDate orderDate) throws FlooringMasteryPersistenceException {
        List<Order> orderList = new ArrayList<Order>(orderData.values());
        int orderNum = 0;
        
        for (Order o : orderList) {
            if (o.getCustomerName().equals(customerName) && o.getOrderDate().equals(orderDate)) {
                orderNum = o.getOrderNumber();
            }
        }
        
        Order notRealOrder;
        if (orderNum == 0) {
            return notRealOrder = new Order(-1);
        } else {
            return orderData.get(orderNum);
        }
    }
    
    @Override
    public int returnHighestOrderNumber() throws FlooringMasteryPersistenceException {
        List<Order> orderList = new ArrayList<Order>(orderData.values());
        int max = 0;
        
        for (Order o : orderList) {
            if (o.getOrderNumber() > max) {
                max = o.getOrderNumber();
            }
        }
        
        return max;
    }
    
    @Override
    public void assignRemovedOrderNumWasHighest(int removedOrderNumber) throws FlooringMasteryPersistenceException {
        this.removedOrderNumWasHighest = removedOrderNumber;
    }
    
    @Override
    public int returnRemovedOrderNumWasHighest() throws FlooringMasteryPersistenceException {
        return removedOrderNumWasHighest;
    }

    //Part of loadFileData()
    private Order unmarshallOrder(String orderAsText){
        //orderAsText is expecting a line read in from our file.
        String[] orderTokens = orderAsText.split(DELIMITER);

        //Create new Order object and add properties to it from the file
        Order orderFromFile = new Order(Integer.parseInt(orderTokens[0]));
        
        orderFromFile.setCustomerName(orderTokens[1]);
        
        String dateString = orderTokens[2];
        LocalDate dateLD = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
        orderFromFile.setOrderDate(dateLD);
        
        orderFromFile.setState(orderTokens[3]);
        
        orderFromFile.setTaxRate(Double.parseDouble(orderTokens[4]));
        
        orderFromFile.setProductType(orderTokens[5]);
        
        orderFromFile.setArea(Double.parseDouble(orderTokens[6]));
        
        orderFromFile.setCostPerSquareFoot(Double.parseDouble(orderTokens[7]));
        
        orderFromFile.setLaborCostPerSquareFoot(Double.parseDouble(orderTokens[8]));
        
        orderFromFile.setMaterialCost(Double.parseDouble(orderTokens[9]));
        
        orderFromFile.setLaborCost(Double.parseDouble(orderTokens[10]));
        
        orderFromFile.setTax(Double.parseDouble(orderTokens[11]));
        
        orderFromFile.setTotal(Double.parseDouble(orderTokens[12]));
        
        //Return new Order object
        return orderFromFile;
    }
    
    //Part of loadFileData()
    private Product unmarshallProduct(String productAsText){
        //productAsText is expecting a line read in from our file.
        String[] productTokens = productAsText.split(DELIMITER);

        //Create new Product object and add properties to it from the file
        Product productFromFile = new Product();
        productFromFile.setProductType(productTokens[0]);
        productFromFile.setCostPerSquareFoot(Double.parseDouble(productTokens[1]));
        productFromFile.setLaborCostPerSquareFoot(Double.parseDouble(productTokens[2])); 
        
        //Return new Product object
        return productFromFile;
    }
    
    //Part of loadFileData()
    private Tax unmarshallTax(String taxAsText){
        //productAsText is expecting a line read in from our file.
        String[] taxTokens = taxAsText.split(DELIMITER);

        //Create new Tax object and add properties to it from the file
        Tax taxFromFile = new Tax();
        taxFromFile.setState(taxTokens[0]);
        taxFromFile.setTaxRate(Double.parseDouble(taxTokens[1])); 
        
        //Return new Product object
        return taxFromFile;
    }
    
    @Override
    public void loadFileData(int mode) throws FlooringMasteryPersistenceException {
        //Loading all the data from Orders, Products, and Taxes
        
        //PART 1: Order Data
        Scanner sc;
        String fileName = null;
        if (mode == 1) {
            fileName = FILE_ORDER;
        } else if (mode == 2) {
            fileName = FILE_ORDER_TRAINING;
        }
        
        try {
            sc = new Scanner(new BufferedReader(new FileReader(fileName)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Could not load Item data into memory.", e);
        }
        
        //currentLine holds the most recent line read from the file
        String currentLine;
        
        Order currentOrder;
        
        //Go through the file line by line, decoding each line into an 
        //Order object by calling the unmarshallOrder method.
        //Process while we have more lines in the file
        while (sc.hasNextLine()) {
            //Get the next line in the file
            currentLine = sc.nextLine();
            
            //Unmarshall the line into an Order
            currentOrder = unmarshallOrder(currentLine);
            
            //Putting all Order objects into the vendingMachine Map in memory
            orderData.put(currentOrder.getOrderNumber(), currentOrder);
        }
        
        //PART 2: Product Data        
        try {
            sc = new Scanner(new BufferedReader(new FileReader(FILE_PRODUCT)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Could not load Item data into memory.", e);
        }
        
        Product currentProduct;
        
        //Go through the file line by line, decoding each line into an 
        //Product object by calling the unmarshallProduct method.
        //Process while we have more lines in the file
        while (sc.hasNextLine()) {
            //Get the next line in the file
            currentLine = sc.nextLine();
            
            //Unmarshall the line into an Order
            currentProduct = unmarshallProduct(currentLine);
            
            //Putting all Order objects into the vendingMachine Map in memory
            productData.put(currentProduct.getProductType(), currentProduct);
        }
        
        //PART 3: Tax Data        
        try {
            sc = new Scanner(new BufferedReader(new FileReader(FILE_TAX)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Could not load Item data into memory.", e);
        }
        
        Tax currentTax;
        
        //Go through the file line by line, decoding each line into an 
        //Tax object by calling the unmarshallProduct method.
        //Process while we have more lines in the file
        while (sc.hasNextLine()) {
            //Get the next line in the file
            currentLine = sc.nextLine();
            
            //Unmarshall the line into an Order
            currentTax = unmarshallTax(currentLine);
            
            //Putting all Order objects into the vendingMachine Map in memory
            taxData.put(currentTax.getState(), currentTax);
        }
    }
    
    //Part of writeFileData()
    //Only need to write Order data to the file
    private String marshallOrder(Order order) {
        //Turning an Item object into a String to be printed into the file
           
        //Add each of the properties from the Item to the String
        String orderAsText = order.getOrderNumber() + DELIMITER;
        orderAsText += order.getCustomerName() + DELIMITER;
        orderAsText += order.getOrderDate() + DELIMITER;
        orderAsText += order.getState() + DELIMITER;
        orderAsText += order.getTaxRate() + DELIMITER;
        orderAsText += order.getProductType() + DELIMITER;
        orderAsText += order.getArea() + DELIMITER;
        orderAsText += order.getCostPerSquareFoot() + DELIMITER;
        orderAsText += order.getLaborCostPerSquareFoot() + DELIMITER;
        orderAsText += order.getMaterialCost() + DELIMITER;
        orderAsText += order.getLaborCost() + DELIMITER;
        orderAsText += order.getTax() + DELIMITER;
        orderAsText += order.getTotal();

        return orderAsText;
    }

    @Override
    public void writeFileData(int mode) throws FlooringMasteryPersistenceException {
        //Only writing the Order objects from memory to the file
        //Product and Tax info only need to be read in
        PrintWriter out;
        String fileName = null;
        if (mode == 1) {
            fileName = FILE_ORDER;
        } else if (mode == 2) {
            fileName = FILE_ORDER_TRAINING;
        }

        try {
            out = new PrintWriter(new FileWriter(fileName));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not save data.", e);
        }

        //Write out Items objects to the file
        String orderAsText;
        List<Order> orderList = this.getAllOrders(); //getting all Order objects from Map in memory
        for (Order currentOrder : orderList) {
            //Make an Order object into a String using the above method
            orderAsText = marshallOrder(currentOrder);
            out.println(orderAsText);
            out.flush(); //Making PrintWriter go to the next line
        }
        
        out.close();
    }
}
