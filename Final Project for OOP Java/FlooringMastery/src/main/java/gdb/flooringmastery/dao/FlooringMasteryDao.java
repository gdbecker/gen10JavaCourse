package gdb.flooringmastery.dao;

import gdb.flooringmastery.dto.Order;
import gdb.flooringmastery.dto.Product;
import gdb.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.util.List;

/**
 * Final Project OOP
 * @author garrettbecker
 */

public interface FlooringMasteryDao {
    /**
     * @MainFunction: get all Order objects from memory
     * 
     * @param: None
     * 
     * @return: List<Order>: list of all Order objects in memory
     */
    List<Order> getAllOrders() throws FlooringMasteryPersistenceException;
    
    List<Product> getAllProducts() throws FlooringMasteryPersistenceException;
    
    List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException;
    
    /**
     * @MainFunction: get Order objects from memory based on date user inputs
     * 
     * @param: None
     * 
     * @return: List<Order>: list of Orders in memory with indicated date
     * 
     * Option 1 on main screen
     */
    List<Order> getOrdersByDate(LocalDate orderDate) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: add an Order to memory 
     * 
     * @param: Order: Order object to add 
     * 
     * @return: return the added Order
     * 
     * Option 2 on main screen
     */
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: edit an existing Order object from memory
     * 
     * @param orderNumber: int identifying the order's number
     * @param newOrder: Order object already in the system 
     * 
     * @return edited Order object (or null if one wasn't found originally)
     * 
     * Option 3 on main screen
     */
    Order editOrder(int orderNumber, Order newOrder) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: remove an Order from memory 
     * 
     * @param orderNumber: int identifying the order's number  
     * 
     * @return: return the removed Order
     * 
     * Option 4 on main screen
     */
    public Order removeOrder(int orderNumber) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: get specific Order from memory
     * 
     * @param orderNumber: int identifying the order's number
     * 
     * @return Order: the Order with customerName & orderDate as part of its properties
     */
    Order getOrder(int orderNumber) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: get highest order number from memory
     * 
     * @param None
     * 
     * @return int: containing the highest order number currently in system
     */
    int returnHighestOrderNumber() throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: load data from OrderData.txt, ProductData.txt, and
     * TaxData.txt from files into memory
     * 
     * @return nothing to return
     */
    public void loadFileData() throws FlooringMasteryPersistenceException;

    /**
     * Main function: write data to OrderData.txt, ProductData.txt, and
     * TaxData.txt files from memory
     * 
     * @return nothing to return
     */
    public void writeFileData() throws FlooringMasteryPersistenceException;
}
