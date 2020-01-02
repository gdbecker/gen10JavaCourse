package gdb.flooringmastery.dao;

import gdb.flooringmastery.dto.Order;
import gdb.flooringmastery.dto.Product;
import gdb.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Final Project OOP
 * @author garrettbecker
 */
public class FlooringMasteryDaoTest {
    
    private FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();
    
    public FlooringMasteryDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    //Very important to have set up since you need to get in a known good state
    //before running tests on stateful code
    @BeforeEach
    public void setUp() throws Exception {
        List<Order> orderList = dao.getAllOrders();
        for (Order o : orderList) {
            dao.removeOrder(o.getOrderNumber());
        }
        
        List<Product> productList = dao.getAllProducts();
        for (Product p : productList) {
            dao.removeProduct(p);
        }
        
        List<Tax> taxList = dao.getAllTaxes();
        for (Tax t : taxList) {
            dao.removeTax(t);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllOrders method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        //Make two Order objects and put into test memory
        Order order1 = new Order(1);
        order1.setCustomerName("Smith");
        order1.setOrderDate(LocalDate.MAX);
        order1.setState("OH");
        order1.setProductType("Wood");
        order1.setArea(12);
        dao.addOrder(order1);
        
        Order order2 = new Order(2);
        order2.setCustomerName("Barker");
        order2.setOrderDate(LocalDate.MAX);
        order2.setState("IN");
        order2.setProductType("Tile");
        order2.setArea(234);
        dao.addOrder(order2);
        
        assertEquals(2, dao.getAllOrders().size());
    }

    /**
     * Test of getAllProducts method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        //Make two Product objects and put into test memory
        Product product1 = new Product();
        product1.setProductType("Concrete");
        product1.setCostPerSquareFoot(2.34);
        product1.setLaborCostPerSquareFoot(3.45);
        dao.addProduct(product1);
        
        Product product2 = new Product();
        product2.setProductType("Glass");
        product2.setCostPerSquareFoot(3.21);
        product2.setLaborCostPerSquareFoot(1.34);
        dao.addProduct(product2);
        
        assertEquals(2, dao.getAllProducts().size());
    }

    /**
     * Test of getAllTaxes method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetAllTaxes() throws Exception {
        //Make two Tax objects and put into test memory
        Tax tax1 = new Tax();
        tax1.setState("GA");
        tax1.setTaxRate(2.45);
        dao.addTax(tax1);
        
        Tax tax2 = new Tax();
        tax2.setState("AL");
        tax2.setTaxRate(1.48);
        dao.addTax(tax2);
        
        assertEquals(2, dao.getAllTaxes().size());
    }

    /**
     * Test of getOrdersByDate method, of class FlooringMasteryDao.
     */
    
    @Test
    public void testGetOrdersByDate() throws Exception {
        //Make two Order objects and put into test memory
        Order order1 = new Order(1);
        order1.setCustomerName("Smith");
        order1.setOrderDate(LocalDate.now());
        order1.setState("OH");
        order1.setProductType("Wood");
        order1.setArea(12);
        dao.addOrder(order1);
        
        Order order2 = new Order(2);
        order2.setCustomerName("Barker");
        order2.setOrderDate(LocalDate.MAX);
        order2.setState("IN");
        order2.setProductType("Tile");
        order2.setArea(234);
        dao.addOrder(order2);
        
        LocalDate today = LocalDate.parse("2020-01-02", DateTimeFormatter.ISO_DATE);
        assertEquals(1, dao.getOrdersByDate(today).size());
    }

    /**
     * Test of addOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testAddOrder() throws Exception {
        //Make one Product objects and put into test memory
        Order order1 = new Order(1);
        order1.setCustomerName("Smith");
        order1.setOrderDate(LocalDate.MAX);
        order1.setState("OH");
        order1.setProductType("Wood");
        order1.setArea(12);
        dao.addOrder(order1);
        
        assertEquals(1, dao.getAllOrders().size());
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        //Make two Order objects and put into test memory
        Order order1 = new Order(1);
        order1.setCustomerName("Smith");
        order1.setOrderDate(LocalDate.MAX);
        order1.setState("OH");
        order1.setProductType("Wood");
        order1.setArea(12);
        dao.addOrder(order1);
        
        Order order2 = new Order(2);
        order2.setCustomerName("Barker");
        order2.setOrderDate(LocalDate.MAX);
        order2.setState("IN");
        order2.setProductType("Tile");
        order2.setArea(234);
        dao.addOrder(order2);
        
        dao.removeOrder(order1.getOrderNumber());
        
        assertEquals(1, dao.getAllOrders().size());
        
        dao.removeOrder(order2.getOrderNumber());
        
        assertEquals(0, dao.getAllOrders().size());
    }

    /**
     * Test of getOrderByOrderNumber method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetOrderByOrderNumber() throws Exception {
        //Make two Order objects and put into test memory
        Order order1 = new Order(1);
        order1.setCustomerName("Smith");
        order1.setOrderDate(LocalDate.MAX);
        order1.setState("OH");
        order1.setProductType("Wood");
        order1.setArea(12);
        dao.addOrder(order1);
        
        Order order2 = new Order(2);
        order2.setCustomerName("Barker");
        order2.setOrderDate(LocalDate.MAX);
        order2.setState("IN");
        order2.setProductType("Tile");
        order2.setArea(234);
        dao.addOrder(order2);
        
        assertEquals(order1, dao.getOrderByOrderNumber(1));
    }

    /**
     * Test of getOrderByNameAndDate method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetOrderByNameAndDate() throws Exception {
        //Make two Order objects and put into test memory
        Order order1 = new Order(1);
        order1.setCustomerName("Smith");
        order1.setOrderDate(LocalDate.MAX);
        order1.setState("OH");
        order1.setProductType("Wood");
        order1.setArea(12);
        dao.addOrder(order1);
        
        Order order2 = new Order(2);
        order2.setCustomerName("Barker");
        order2.setOrderDate(LocalDate.MAX);
        order2.setState("IN");
        order2.setProductType("Tile");
        order2.setArea(234);
        dao.addOrder(order2);
        
        assertEquals(order1, dao.getOrderByNameAndDate("Smith", LocalDate.MAX));
    }

    /**
     * Test of returnHighestOrderNumber method, of class FlooringMasteryDao.
     */
    @Test
    public void testReturnHighestOrderNumber() throws Exception {
        //Make three Order objects and put into test memory
        Order order1 = new Order(1);
        order1.setCustomerName("Smith");
        order1.setOrderDate(LocalDate.MAX);
        order1.setState("OH");
        order1.setProductType("Wood");
        order1.setArea(12);
        dao.addOrder(order1);
        
        Order order2 = new Order(2);
        order2.setCustomerName("Barker");
        order2.setOrderDate(LocalDate.MAX);
        order2.setState("IN");
        order2.setProductType("Tile");
        order2.setArea(234);
        dao.addOrder(order2);
        
        Order order3 = new Order(3);
        order3.setCustomerName("Adams");
        order3.setOrderDate(LocalDate.MAX);
        order3.setState("PA");
        order3.setProductType("Laminate");
        order3.setArea(91);
        dao.addOrder(order3);
        
        assertEquals(3, dao.returnHighestOrderNumber());
    }

    /**
     * Test of assignRemovedOrderNumWasHighest method, of class FlooringMasteryDao.
     */
    @Test
    public void testAssignRemovedOrderNumWasHighest() throws Exception {
        //Make three Order objects and put into test memory
        Order order1 = new Order(1);
        order1.setCustomerName("Smith");
        order1.setOrderDate(LocalDate.MAX);
        order1.setState("OH");
        order1.setProductType("Wood");
        order1.setArea(12);
        dao.addOrder(order1);
        
        Order order2 = new Order(2);
        order2.setCustomerName("Barker");
        order2.setOrderDate(LocalDate.MAX);
        order2.setState("IN");
        order2.setProductType("Tile");
        order2.setArea(234);
        dao.addOrder(order2);
        
        Order order3 = new Order(3);
        order3.setCustomerName("Adams");
        order3.setOrderDate(LocalDate.MAX);
        order3.setState("PA");
        order3.setProductType("Laminate");
        order3.setArea(91);
        dao.addOrder(order3);
        
        int highestOrderNum = dao.returnHighestOrderNumber();
        Order removedOrder = dao.removeOrder(3);
        if(removedOrder.getOrderNumber() == highestOrderNum) {
            dao.assignRemovedOrderNumWasHighest(highestOrderNum);
        }
        
        assertEquals(3, dao.returnRemovedOrderNumWasHighest());
    }

    /**
     * Test of returnRemovedOrderNumWasHighest method, of class FlooringMasteryDao.
     */
    @Test
    public void testReturnRemovedOrderNumWasHighest() throws Exception {
        //Make three Order objects and put into test memory
        Order order1 = new Order(1);
        order1.setCustomerName("Smith");
        order1.setOrderDate(LocalDate.MAX);
        order1.setState("OH");
        order1.setProductType("Wood");
        order1.setArea(12);
        dao.addOrder(order1);
        
        Order order2 = new Order(2);
        order2.setCustomerName("Barker");
        order2.setOrderDate(LocalDate.MAX);
        order2.setState("IN");
        order2.setProductType("Tile");
        order2.setArea(234);
        dao.addOrder(order2);
        
        Order order3 = new Order(3);
        order3.setCustomerName("Adams");
        order3.setOrderDate(LocalDate.MAX);
        order3.setState("PA");
        order3.setProductType("Laminate");
        order3.setArea(91);
        dao.addOrder(order3);
        
        int highestOrderNum = dao.returnHighestOrderNumber();
        Order removedOrder = dao.removeOrder(3);
        if(removedOrder.getOrderNumber() == highestOrderNum) {
            dao.assignRemovedOrderNumWasHighest(highestOrderNum);
        }
        
        assertEquals(3, dao.returnRemovedOrderNumWasHighest());
    }
}
