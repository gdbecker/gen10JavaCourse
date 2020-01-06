package gdb.vendingmachine.dao;

import gdb.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

public class VendingMachineDaoTest {
    
    private VendingMachineDao dao = new VendingMachineDaoFileImpl();
    
    public VendingMachineDaoTest() {
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
        List<Item> itemList = dao.getAllVendingMachineItems();
        
        for (Item i : itemList) {
            dao.removeItem(i.getName());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAvailableVendingMachineItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAvailableVendingMachineItems() throws Exception {
        //Add two Item objects, one with inventory and another without
        //Test to make sure that code only grabs the Item with inventory
        Item item1 = new Item();
        item1.setName("Dasani");
        item1.setPrice(BigDecimal.TEN);
        item1.setInventory(10);
        
        dao.addItem(item1);
        
        Item item2 = new Item();
        item2.setName("Muscle Milk");
        item2.setPrice(BigDecimal.TEN);
        item2.setInventory(0);
        
        dao.addItem(item2);
        
        assertEquals(1, dao.getAvailableVendingMachineItems().size());
    }

    /**
     * Test of getAllVendingMachineItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllVendingMachineItems() throws Exception {
        //Add two Item objects, both with inventory
        //Test to make sure that code grabs both
        Item item1 = new Item();
        item1.setName("Dasani");
        item1.setPrice(BigDecimal.TEN);
        item1.setInventory(10);
        
        dao.addItem(item1);
        
        Item item2 = new Item();
        item2.setName("Muscle Milk");
        item2.setPrice(BigDecimal.TEN);
        item2.setInventory(7);
        
        dao.addItem(item2);
        
        assertEquals(2, dao.getAvailableVendingMachineItems().size());
    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     */
    @Test
    public void testGetItem() throws Exception {
        //Create new Item with all fields filled in
        Item item1 = new Item();
        item1.setName("Dasani");
        item1.setPrice(BigDecimal.TEN);
        item1.setInventory(10);
        
        //Add it to memory
        dao.addItem(item1);
        
        //Now try getting it from memory
        Item daoItem = dao.getItem(item1.getName());
        
        //Make sure that the pointers point to the same object
        assertEquals(item1, daoItem);
    }

    /**
     * Test of getChange method, of class VendingMachineDao.
     */
    @Test
    public void testGetChange() throws Exception {
        //Create new Item with all fields filled in
        Item item1 = new Item();
        item1.setName("Dasani");
        item1.setPrice(BigDecimal.TEN);
        item1.setInventory(10);
        
        //Add it to memory
        dao.addItem(item1);
        
        //Create BigDecimal for amount user put in
        BigDecimal userInput = new BigDecimal("3.00");
        
        //Get Map of change values
        Map<Coin, BigDecimal> change = dao.getChange(userInput, item1.getPrice());
        
        //Make sure number of quarters in change is equal
        Coin coinQ = Coin.QUARTER;
        BigDecimal numQ = new BigDecimal("-28");
        assertEquals(numQ, change.get(coinQ));
    }

    /**
     * Test of updateInventory method, of class VendingMachineDao.
     */
    @Test
    public void testUpdateInventory() throws Exception {
        //Create new Item with all fields filled in
        Item item1 = new Item();
        item1.setName("Dasani");
        item1.setPrice(BigDecimal.ONE);
        item1.setInventory(10);
        
        //Add it to memory
        dao.addItem(item1);
        
        //Update inventory
        dao.updateInventory(item1);
        
        //Make sure that inventory actually decreased by 1
        assertEquals(9, item1.getInventory());
    }
}
