package gdb.flooringmastery.service;

import gdb.flooringmastery.dao.FlooringMasteryPersistenceException;
import gdb.flooringmastery.dto.Order;
import gdb.flooringmastery.dto.Product;
import gdb.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.util.List;

/**
 * Final Project OOP
 * @author garrettbecker
 */

public interface FlooringMasteryService {
    
    //Matches up with coresponding DAO method
    List<Order> getAllOrders() throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    List<Product> getAllProducts() throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    List<Order> getOrdersByDate(LocalDate orderDate) throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    public Order removeOrder(int orderNumber) throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    Order getOrderByOrderNumber(int orderNumber) throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    public Order getOrderByNameAndDate(String customerName, LocalDate orderDate) throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    int returnHighestOrderNumber() throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    void assignRemovedOrderNumWasHighest(int removedOrderNumber) throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    int returnRemovedOrderNumWasHighest() throws FlooringMasteryPersistenceException;
    
    Order fillRemainingOrderDetailsFromAddOrder(Order newOrder) throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    public void loadFileData(int mode) throws FlooringMasteryPersistenceException;

    //Matches up with coresponding DAO method
    public void writeFileData(int mode) throws FlooringMasteryPersistenceException;
}
