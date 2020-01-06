package gdb.flooringmastery.service;

import gdb.flooringmastery.dao.FlooringMasteryAuditDao;
import gdb.flooringmastery.dao.FlooringMasteryAuditDaoFileImpl;
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
    FlooringMasteryDao mainDao = new FlooringMasteryDaoFileImpl();
    FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoFileImpl();

    //Constructor
    public FlooringMasteryServiceImpl(FlooringMasteryDao mainDao, FlooringMasteryAuditDao auditDao) {
        this.mainDao = mainDao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException {
        return mainDao.getAllOrders();
    }
    
    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        return mainDao.getAllProducts();
    }

    @Override
    public List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
        return mainDao.getAllTaxes();
    }
    
    @Override
    public List<Order> getOrdersByDate(LocalDate orderDate) throws FlooringMasteryPersistenceException {
        return mainDao.getOrdersByDate(orderDate);
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {
        return mainDao.addOrder(order);
    }
    
    @Override
    public Product addProduct(Product product) throws FlooringMasteryPersistenceException {
        return mainDao.addProduct(product);
    }

    @Override
    public Tax addTax(Tax tax) throws FlooringMasteryPersistenceException {
        return mainDao.addTax(tax);
    }

    @Override
    public Order removeOrder(int orderNumber) throws FlooringMasteryPersistenceException {
        return mainDao.removeOrder(orderNumber);
    }
    
    @Override
    public Product removeProduct(Product product) throws FlooringMasteryPersistenceException {
        return mainDao.removeProduct(product);
    }

    @Override
    public Tax removeTax(Tax tax) throws FlooringMasteryPersistenceException {
        return mainDao.removeTax(tax);
    }

    @Override
    public Order getOrderByOrderNumber(int orderNumber) throws FlooringMasteryPersistenceException {
        return mainDao.getOrderByOrderNumber(orderNumber);
    }
    
    @Override
    public Order getOrderByNameAndDate(String customerName, LocalDate orderDate) throws FlooringMasteryPersistenceException {
        return mainDao.getOrderByNameAndDate(customerName, orderDate);
    }
    
    @Override
    public int returnHighestOrderNumber() throws FlooringMasteryPersistenceException {
        return mainDao.returnHighestOrderNumber();
    }
    
    @Override
    public void assignRemovedOrderNumWasHighest(int removedOrderNumber) throws FlooringMasteryPersistenceException {
        mainDao.assignRemovedOrderNumWasHighest(removedOrderNumber);
    }
    
    @Override
    public int returnRemovedOrderNumWasHighest() throws FlooringMasteryPersistenceException {
        return mainDao.returnRemovedOrderNumWasHighest();
    }
    
    @Override
    public Order fillRemainingOrderDetailsFromAddOrder(Order newOrder) throws FlooringMasteryPersistenceException {
        List<Product> productList = mainDao.getAllProducts();
        List<Tax> taxList = mainDao.getAllTaxes();
        
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
        tax = taxBD.doubleValue();
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
        mainDao.loadFileData(mode);
    }

    @Override
    public void writeFileData(int mode) throws FlooringMasteryPersistenceException {
        mainDao.writeFileData(mode);
    }

    //For option 1: View all Orders & option 6: Save current work
    @Override
    public void writeAuditLog(int option, int mode) throws FlooringMasteryPersistenceException {
        String modeString = null;
        
        if (mode == 1) {
            modeString = "Production Mode";
        } else if (mode == 2) {
            modeString = "Training Mode";
        }
        
        switch (option) {
            case 1:
                auditDao.writeAuditEntry("(" + modeString + ") User viewed all orders within the system.");
                break;
            case 6:
                if (mode == 1) {
                    auditDao.writeAuditEntry("(" + modeString + ") Data manually saved to the file 'OrderData.txt'");
                } else if (mode == 2) {
                    auditDao.writeAuditEntry("(" + modeString + ") No data manually saved, only practice for training");
                }
                break;
            default:
                break;
        }
        
    }
          
    //For option 2: Display Orders by date input
    @Override
    public void writeAuditLog(LocalDate date, int mode) throws FlooringMasteryPersistenceException {
        String modeString = null;
        
        if (mode == 1) {
            modeString = "Production Mode";
        } else if (mode == 2) {
            modeString = "Training Mode";
        }
        
        auditDao.writeAuditEntry("(" + modeString + ") User viewed all orders with " + date + " date.");
    }
    
    //For options 3-5: adding, editing, and removing Orders
    @Override
    public void writeAuditLog(Order order, int option, int mode) throws FlooringMasteryPersistenceException {
        String modeString = null;
        
        if (mode == 1) {
            modeString = "Production Mode";
        } else if (mode == 2) {
            modeString = "Training Mode";
        }
        
        switch (option) {
            case 3:
                auditDao.writeAuditEntry("(" + modeString + ") Order for " + order.getCustomerName() + " and date " + order.getOrderDate() + " was added to the system.");
                break;
            case 4:
                auditDao.writeAuditEntry("(" + modeString + ") Order for " + order.getCustomerName() + " and date " + order.getOrderDate() + " was edited.");
                break;
            case 5:
                auditDao.writeAuditEntry("(" + modeString + ") Order for " + order.getCustomerName() + " and date " + order.getOrderDate() + " was deleted from the system.");
                break;
            default:
                break;
        }
    }
}
