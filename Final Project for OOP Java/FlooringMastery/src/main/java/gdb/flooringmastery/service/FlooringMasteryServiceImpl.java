package gdb.flooringmastery.service;

import gdb.flooringmastery.dao.FlooringMasteryDao;
import gdb.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import gdb.flooringmastery.dao.FlooringMasteryPersistenceException;
import gdb.flooringmastery.dto.Order;
import gdb.flooringmastery.dto.Product;
import gdb.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 * Final Project OOP
 * @author garrettbecker
 */

public class FlooringMasteryServiceImpl implements FlooringMasteryService {
    FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();

    //Constructor
    public FlooringMasteryServiceImpl(FlooringMasteryDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException {
        return dao.getAllOrders();
    }
    
    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        return dao.getAllProducts();
    }

    @Override
    public List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
        return dao.getAllTaxes();
    }
    
    @Override
    public List<Order> getOrdersByDate(LocalDate orderDate) throws FlooringMasteryPersistenceException {
        return dao.getOrdersByDate(orderDate);
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {
        return dao.addOrder(order);
    }

    @Override
    public Order removeOrder(int orderNumber) throws FlooringMasteryPersistenceException {
        return dao.removeOrder(orderNumber);
    }

    @Override
    public Order getOrderByOrderNumber(int orderNumber) throws FlooringMasteryPersistenceException {
        return dao.getOrderByOrderNumber(orderNumber);
    }
    
    @Override
    public Order getOrderByNameAndDate(String customerName, LocalDate orderDate) throws FlooringMasteryPersistenceException {
        return dao.getOrderByNameAndDate(customerName, orderDate);
    }
    
    @Override
    public int returnHighestOrderNumber() throws FlooringMasteryPersistenceException {
        return dao.returnHighestOrderNumber();
    }
    
    @Override
    public void assignRemovedOrderNumWasHighest(int removedOrderNumber) throws FlooringMasteryPersistenceException {
        dao.assignRemovedOrderNumWasHighest(removedOrderNumber);
    }
    
    @Override
    public int returnRemovedOrderNumWasHighest() throws FlooringMasteryPersistenceException {
        return dao.returnRemovedOrderNumWasHighest();
    }
    
    @Override
    public Order fillRemainingOrderDetailsFromAddOrder(Order newOrder) throws FlooringMasteryPersistenceException {
        List<Product> productList = dao.getAllProducts();
        List<Tax> taxList = dao.getAllTaxes();
        
        //Match up the product type from the new order with one within the product list
        //If there's a match, then get the costs associated with that type and add
        //them into the newOrder
        for (Product p : productList) {
            if (newOrder.getProductType().equalsIgnoreCase(p.getProductType())) {
                newOrder.setCostPerSquareFoot(p.getCostPerSquareFoot());
                newOrder.setLaborCostPerSquareFoot(p.getLaborCostPerSquareFoot());
            }
        }
        
        //Match up the state tax from the new order with one within the tax list
        //If there's a match, then get the tax associated with that state and add
        //it into the newOrder
        for (Tax t : taxList) {
            if (newOrder.getState().equalsIgnoreCase(t.getState())) {
                newOrder.setTaxRate(t.getTaxRate());
            }
        }
        
        //Do calculations to fill in the rest of the order
        //Material Cost
        double materialCost = newOrder.getArea() * newOrder.getCostPerSquareFoot();
        String materialCostString = String.valueOf(materialCost);
        BigDecimal materialCostBD = new BigDecimal(materialCostString);
        materialCostBD = materialCostBD.setScale(2, RoundingMode.HALF_UP);
        materialCost = materialCostBD.doubleValue();
        newOrder.setMaterialCost(materialCost);
        
        //Labor Cost
        double laborCost = newOrder.getArea() * newOrder.getLaborCostPerSquareFoot();
        String laborCostString = String.valueOf(laborCost);
        BigDecimal laborCostBD = new BigDecimal(laborCostString);
        laborCostBD = laborCostBD.setScale(2, RoundingMode.HALF_UP);
        laborCost = laborCostBD.doubleValue();
        newOrder.setLaborCost(laborCost);
        
        //Tax Cost
        double tax = (newOrder.getMaterialCost() + newOrder.getLaborCost()) * (newOrder.getTaxRate() / 100);
        String taxString = String.valueOf(tax);
        BigDecimal taxBD = new BigDecimal(taxString);
        taxBD = taxBD.setScale(2, RoundingMode.HALF_UP);
        tax = laborCostBD.doubleValue();
        newOrder.setTax(tax);
        
        //Total Cost
        double total = newOrder.getMaterialCost() + newOrder.getLaborCost() + newOrder.getTax();
        String totalString = String.valueOf(total);
        BigDecimal totalBD = new BigDecimal(totalString);
        totalBD = totalBD.setScale(2, RoundingMode.HALF_UP);
        total = totalBD.doubleValue();
        newOrder.setTotal(total);
        
        return newOrder;
    }

    @Override
    public void loadFileData(int mode) throws FlooringMasteryPersistenceException {
        dao.loadFileData(mode);
    }

    @Override
    public void writeFileData(int mode) throws FlooringMasteryPersistenceException {
        dao.writeFileData(mode);
    }
}
