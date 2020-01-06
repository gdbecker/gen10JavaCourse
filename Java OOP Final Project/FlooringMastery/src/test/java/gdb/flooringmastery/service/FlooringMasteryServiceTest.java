package gdb.flooringmastery.service;

import gdb.flooringmastery.dao.FlooringMasteryDao;
import gdb.flooringmastery.dao.FlooringMasteryDaoStubImpl;
import gdb.flooringmastery.dao.FlooringMasteryPersistenceException;
import gdb.flooringmastery.dto.Order;
import gdb.flooringmastery.dto.Product;
import gdb.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Final Project OOP
 * @author garrettbecker
 */
public class FlooringMasteryServiceTest {
    
    private FlooringMasteryService service;
    
    public FlooringMasteryServiceTest() {
        /*
        //Use the stub version of DAO to create the service
        FlooringMasteryDao dao = new FlooringMasteryDaoStubImpl();
        
        //Create the service object for testing purposes
        service = new FlooringMasteryServiceImpl(dao);
        */
        
        //Replacing above code with new code for Spring Dependency
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", FlooringMasteryService.class);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllOrders method, of class FlooringMasteryService.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        //Add another Order to the test memory
        Order order = new Order(2);
        order.setCustomerName("Adams");
        order.setOrderDate(LocalDate.parse("2019-01-02"));
        order.setState("OH");
        order.setProductType("Wood");
        order.setArea(10);
        service.addOrder(order);
        
        //Get all Order objects from test memory
        List<Order> orderList = service.getAllOrders();
        
        //Make sure all Orders were pulled
        assertEquals(2, orderList.size());
    }

    /**
     * Test of getAllProducts method, of class FlooringMasteryService.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        //Add another Product to the test memory
        Product product = new Product();
        product.setProductType("Wood");
        product.setCostPerSquareFoot(2.25);
        product.setLaborCostPerSquareFoot(3.25);
        service.addProduct(product);
        
        //Get all Product objects from test memory
        List<Product> productList = service.getAllProducts();
        
        //Make sure all Products were pulled
        assertEquals(2, productList.size());
    }

    /**
     * Test of getAllTaxes method, of class FlooringMasteryService.
     */
    @Test
    public void testGetAllTaxes() throws Exception {
        //Add another Tax to the test memory
        Tax tax = new Tax();
        tax.setState("OH");
        tax.setTaxRate(2.00);
        service.addTax(tax);
        
        //Get all Tax objects from test memory
        List<Tax> taxList = service.getAllTaxes();
        
        //Make sure all Taxes were pulled
        assertEquals(2, taxList.size());
    }

    /**
     * Test of getOrdersByDate method, of class FlooringMasteryService.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {
        //Add another Order to the test memory
        Order order = new Order(2);
        order.setCustomerName("Adams");
        order.setOrderDate(LocalDate.parse("2019-01-02"));
        order.setState("OH");
        order.setProductType("Wood");
        order.setArea(10);
        service.addOrder(order);
        
        //Verify that only one Order was pulled
        assertEquals(1, service.getOrdersByDate(LocalDate.parse("2019-01-02")).size());
    }

    /**
     * Test of addOrder method, of class FlooringMasteryService.
     */
    @Test
    public void testAddOrder() throws Exception {
        //Make sure that there's one Order in test memory
        assertEquals(1, service.getAllOrders().size());
        
        //Add another Order to the test memory
        Order order = new Order(2);
        order.setCustomerName("Adams");
        order.setOrderDate(LocalDate.parse("2019-01-02"));
        order.setState("OH");
        order.setProductType("Wood");
        order.setArea(10);
        service.addOrder(order);
        
        //Make sure that there are now two Orders in test memory
        assertEquals(2, service.getAllOrders().size());
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryService.
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
        service.addOrder(order1);
        
        Order order2 = new Order(2);
        order2.setCustomerName("Barker");
        order2.setOrderDate(LocalDate.MAX);
        order2.setState("IN");
        order2.setProductType("Tile");
        order2.setArea(234);
        service.addOrder(order2);
        
        service.removeOrder(order1.getOrderNumber());
        
        assertEquals(1, service.getAllOrders().size());
        
        service.removeOrder(order2.getOrderNumber());
        
        assertEquals(0, service.getAllOrders().size());
    }

    /**
     * Test of getOrderByOrderNumber method, of class FlooringMasteryService.
     */
    @Test
    public void testGetOrderByOrderNumber() throws Exception {
        //Add another Order to the test memory
        Order order = new Order(2);
        order.setCustomerName("Adams");
        order.setOrderDate(LocalDate.parse("2019-01-02"));
        order.setState("OH");
        order.setProductType("Wood");
        order.setArea(10);
        service.addOrder(order);
        
        assertEquals(order, service.getOrderByOrderNumber(2));
    }

    /**
     * Test of getOrderByNameAndDate method, of class FlooringMasteryService.
     */
    @Test
    public void testGetOrderByNameAndDate() throws Exception {
        //Get existing Order from test memory
        Order stubOrder = service.getOrderByOrderNumber(1);
        
        //Add another Order to the test memory
        Order order = new Order(2);
        order.setCustomerName("Adams");
        order.setOrderDate(LocalDate.parse("2019-01-02"));
        order.setState("OH");
        order.setProductType("Wood");
        order.setArea(10);
        service.addOrder(order);
        
        assertEquals(stubOrder, service.getOrderByNameAndDate("Smith", LocalDate.parse("2020-01-02")));
        assertEquals(order, service.getOrderByNameAndDate("Adams", LocalDate.parse("2019-01-02")));
    }

    /**
     * Test of returnHighestOrderNumber method, of class FlooringMasteryService.
     */
    @Test
    public void testReturnHighestOrderNumber() throws Exception {
        //Add another Order to the test memory
        Order order = new Order(2);
        order.setCustomerName("Adams");
        order.setOrderDate(LocalDate.parse("2019-01-02"));
        order.setState("OH");
        order.setProductType("Wood");
        order.setArea(10);
        service.addOrder(order);
        
        int highestOrderNum = service.returnHighestOrderNumber();
        
        assertEquals(2, highestOrderNum);
    }

    /**
     * Test of assignRemovedOrderNumWasHighest method, of class FlooringMasteryService.
     */
    @Test
    public void testAssignRemovedOrderNumWasHighest() throws Exception {
        service.assignRemovedOrderNumWasHighest(3);
        assertEquals(3, service.returnRemovedOrderNumWasHighest());
    }

    /**
     * Test of returnRemovedOrderNumWasHighest method, of class FlooringMasteryService.
     */
    @Test
    public void testReturnRemovedOrderNumWasHighest() throws Exception {
        assertEquals(2, service.returnRemovedOrderNumWasHighest());
    }

    /**
     * Test of fillRemainingOrderDetailsFromAddOrder method, of class FlooringMasteryService.
     */
    @Test
    public void testFillRemainingOrderDetailsFromAddOrder() throws Exception {
        //Fill in details of existing Order in test memory
        Order updatedOrder = service.fillRemainingOrderDetailsFromAddOrder(service.getOrderByOrderNumber(1));
        
        //Make sure math was done correctly
        assertEquals(36.00, updatedOrder.getMaterialCost());
        assertEquals(30.00, updatedOrder.getLaborCost());
        assertEquals(2.00, updatedOrder.getTaxRate());
        assertEquals(1.32, updatedOrder.getTax());
        assertEquals(67.32, updatedOrder.getTotal());
        
    }
}
