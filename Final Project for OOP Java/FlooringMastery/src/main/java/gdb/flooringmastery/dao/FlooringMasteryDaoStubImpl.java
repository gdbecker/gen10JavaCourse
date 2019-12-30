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

public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao {

    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Order> getOrdersByDate(LocalDate orderDate) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order editOrder(int orderNumber, Order newOrder) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(int orderNumber) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order getOrder(int orderNumber) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int returnHighestOrderNumber() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadFileData() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeFileData() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
