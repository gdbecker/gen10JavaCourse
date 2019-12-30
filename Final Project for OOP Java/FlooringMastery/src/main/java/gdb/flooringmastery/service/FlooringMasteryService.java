package gdb.flooringmastery.service;

import gdb.flooringmastery.dao.FlooringMasteryPersistenceException;
import gdb.flooringmastery.dto.Order;
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
    List<Order> getOrdersByDate(LocalDate orderDate) throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    Order editOrder(int orderNumber, Order newOrder) throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    public Order removeOrder(int orderNumber) throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    Order getOrder(int orderNumber) throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    int returnHighestOrderNumber() throws FlooringMasteryPersistenceException;
    
    Order fillRemainingOrderDetailsFromAddOrder(Order newOrder) throws FlooringMasteryPersistenceException;
    
    //Matches up with coresponding DAO method
    public void loadFileData() throws FlooringMasteryPersistenceException;

    //Matches up with coresponding DAO method
    public void writeFileData() throws FlooringMasteryPersistenceException;
}
