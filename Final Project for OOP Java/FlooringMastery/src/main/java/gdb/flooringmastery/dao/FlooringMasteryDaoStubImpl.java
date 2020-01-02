package gdb.flooringmastery.dao;

import gdb.flooringmastery.dto.Order;
import gdb.flooringmastery.dto.Product;
import gdb.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Final Project OOP
 * @author garrettbecker
 */

//This is basically a dumbed down version of the VendingMachineDaoFileImpl file
//This exists for the purpose of testing that these methods work
public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao {
    
    Order onlyOrder;
    Product onlyProduct;
    Tax onlyTax;
    Map<Integer, Order> orderData = new HashMap<>();
    Map<String, Product> productData = new HashMap<>();
    Map<String, Tax> taxData = new HashMap<>();
    int removedOrderNumWasHighest;
    
    //Create a default constructor with info filled in so that there's data
    //for the service test to use

    public FlooringMasteryDaoStubImpl() {
        onlyOrder = new Order(1);
        onlyOrder.setCustomerName("Smith");
        onlyOrder.setOrderDate(LocalDate.parse("2020-01-02"));
        onlyOrder.setState("PA");
        onlyOrder.setProductType("Tile");
        onlyOrder.setArea(12);
        orderData.put(onlyOrder.getOrderNumber(), onlyOrder);
        
        onlyProduct = new Product();
        onlyProduct.setProductType("Tile");
        onlyProduct.setCostPerSquareFoot(3.00);
        onlyProduct.setLaborCostPerSquareFoot(2.50);
        productData.put(onlyProduct.getProductType(), onlyProduct);
        
        onlyTax = new Tax();
        onlyTax.setState("PA");
        onlyTax.setTaxRate(2.00);
        taxData.put(onlyTax.getState(), onlyTax);
        
        removedOrderNumWasHighest = 2;
    }
    
    
    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException {
        return orderData.values()
                .stream()
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        return productData.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
        return taxData.values()
                .stream()
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Order> getOrdersByDate(LocalDate orderDate) throws FlooringMasteryPersistenceException {
        return orderData.values()
                .stream()
                .filter(o -> o.getOrderDate().equals(orderDate))
                .collect(Collectors.toList());
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {
        orderData.put(order.getOrderNumber(), order);
        return order;
    }
    
    @Override
    public Product addProduct(Product product) throws FlooringMasteryPersistenceException {
        productData.put(product.getProductType(), product);
        return product;
    }

    @Override
    public Tax addTax(Tax tax) throws FlooringMasteryPersistenceException {
        taxData.put(tax.getState(), tax);
        return tax;
    }

    @Override
    public Order removeOrder(int orderNumber) throws FlooringMasteryPersistenceException {
        return orderData.remove(orderNumber);
    }
    
    @Override
    public Product removeProduct(Product product) throws FlooringMasteryPersistenceException {
        return productData.remove(product.getProductType());
    }

    @Override
    public Tax removeTax(Tax tax) throws FlooringMasteryPersistenceException {
        return taxData.remove(tax.getState());
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
        removedOrderNumWasHighest = removedOrderNumber;
    }
    
    @Override
    public int returnRemovedOrderNumWasHighest() throws FlooringMasteryPersistenceException {
        return removedOrderNumWasHighest;
    }

    @Override
    public void loadFileData(int mode) throws FlooringMasteryPersistenceException {
        //Do nothing
    }

    @Override
    public void writeFileData(int mode) throws FlooringMasteryPersistenceException {
        //Do nothing
    }   
}
