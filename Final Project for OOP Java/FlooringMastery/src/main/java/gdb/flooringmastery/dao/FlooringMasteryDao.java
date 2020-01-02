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
     * 
     * Option 1 on main screen
     * Also used in other methods to get all Order objects
     */
    List<Order> getAllOrders() throws FlooringMasteryPersistenceException;
    
    /**
     * @MainFunction: get all Product objects from memory
     * 
     * @param: None
     * 
     * @return: List<Product>: list of all Product objects in memory
     */
    List<Product> getAllProducts() throws FlooringMasteryPersistenceException;
    
    /**
     * @MainFunction: get all Tax objects from memory
     * 
     * @param: None
     * 
     * @return: List<Tax>: list of all Tax objects in memory
     */
    List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException;
    
    /**
     * @MainFunction: get Order objects from memory based on date user inputs, put into a list
     * 
     * @param: None
     * 
     * @return: List<Order>: list of Orders in memory with indicated date
     * 
     * Option 2 on main screen
     */
    List<Order> getOrdersByDate(LocalDate orderDate) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: add an Order object to memory 
     * 
     * @param: order: Order object to add 
     * 
     * @return: return the added Order
     * 
     * Option 3 on main screen
     */
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: add a Product object to memory 
     * 
     * @param: product: Product object to add 
     * 
     * @return: return the added Product
     */
    public Product addProduct(Product product) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: add a Tax object to memory 
     * 
     * @param: tax: Tax object to add 
     * 
     * @return: return the added Tax
     */
    public Tax addTax(Tax tax) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: remove an Order from memory 
     * 
     * @param orderNumber: int identifying the order's number  
     * 
     * @return: return the removed Order
     * 
     * Option 5 on main screen
     */
    public Order removeOrder(int orderNumber) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: remove a Product from memory 
     * 
     * @param product: Product to remove  
     * 
     * @return: return the removed Product
     */
    public Product removeProduct(Product product) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: remove a Tax from memory 
     * 
     * @param tax: Tax to remove  
     * 
     * @return: return the removed Tax
     */
    public Tax removeTax(Tax tax) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: get specific Order from memory
     * 
     * @param orderNumber: int identifying the order's number
     * 
     * @return Order: the Order with orderNumber as it's main property
     */
    Order getOrderByOrderNumber(int orderNumber) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: get specific Order from memory
     * 
     * @param customerName: String with name of customer 
     * @param orderDate: LocalDate date of order 
     * 
     * @return Order: the Order with customerName & orderDate as part of its properties
     */
    Order getOrderByNameAndDate(String customerName, LocalDate orderDate) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: get highest order number from memory
     * 
     * @param None
     * 
     * @return int: containing the highest order number currently in system
     */
    int returnHighestOrderNumber() throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: assign "removedOrderNumWasHighest" int in DAO if the removed
     * order was the highest on the list; so that when the user tries to make another
     * order, it won't assign it that value
     * 
     * @param removedOrderNumber: int containing the removed order's number, which
     * is the highest in the system
     * 
     * @return: nothing to return
     */
    void assignRemovedOrderNumWasHighest(int removedOrderNumber) throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: get "removedOrderNumWasHighest" in DAO; so that within the 
     * addOrder method, the next order number assigned won't be the highest number
     * that was deleted earlier
     * 
     * @param None
     * 
     * @return int: containing "removedOrderNumWasHighest"
     */
    int returnRemovedOrderNumWasHighest() throws FlooringMasteryPersistenceException;
    
    /**
     * Main function: load data from OrderData.txt, ProductData.txt, and
     * TaxData.txt from files into memory
     * 
     * @return: nothing to return
     */
    public void loadFileData(int mode) throws FlooringMasteryPersistenceException;

    /**
     * Main function: write data to OrderData.txt, ProductData.txt, and
     * TaxData.txt files from memory
     * 
     * @return: nothing to return
     */
    public void writeFileData(int mode) throws FlooringMasteryPersistenceException;
}
