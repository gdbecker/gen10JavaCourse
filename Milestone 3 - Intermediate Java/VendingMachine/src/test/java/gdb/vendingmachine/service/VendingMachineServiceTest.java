package gdb.vendingmachine.service;

import gdb.vendingmachine.dao.Coin;
import gdb.vendingmachine.dao.VendingMachineAuditDao;
import gdb.vendingmachine.dao.VendingMachineAuditDaoStubImpl;
import gdb.vendingmachine.dao.VendingMachineDao;
import gdb.vendingmachine.dao.VendingMachineDaoStubImpl;
import gdb.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

public class VendingMachineServiceTest {
    
    private VendingMachineService service;
    
    public VendingMachineServiceTest() {
        //Use the stub DAO versions for making the dao
        VendingMachineDao mainDao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        
        //Create the tester service object for the purposes of testing
        //Needs both DAO stub versions to work
        service = new VendingMachineServiceImpl(mainDao, auditDao);
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
     * Test of getAvailableVendingMachineItems method, of class VendingMachineService.
     */
    @Test
    public void testGetAvailableVendingMachineItems() throws Exception {
        //Add another Item to testing memory, with 0 inventory
        Item item = new Item();
        item.setName("Cheese Crackers");
        item.setPrice(BigDecimal.ONE);
        item.setInventory(0);
        
        //Add it to memory
        service.addItem(item);
        
        //Get all Items from testing memory
        List<Item> availItems = service.getAvailableVendingMachineItems();
        
        //Make sure that this only got 1 available item
        assertEquals(1, availItems.size());
        
    }

    /**
     * Test of getAllVendingMachineItems method, of class VendingMachineService.
     */
    @Test
    public void testGetAllVendingMachineItems() throws Exception {
        //Add another Item to testing memory
        Item item = new Item();
        item.setName("Cheese Crackers");
        item.setPrice(BigDecimal.ONE);
        item.setInventory(8);
        
        //Add it to memory
        service.addItem(item);
        
        //Get all Items from testing memory
        List<Item> availItems = service.getAllVendingMachineItems();
        
        //Make sure that this got them all
        assertEquals(2, availItems.size());
    }

    /**
     * Test of getItem method, of class VendingMachineService.
     */
    @Test
    public void testGetItem() throws Exception {
        //Get the mp3 info from the stub file and make sure it's not null
        Item thisItem = service.getItem("Dasani");
        assertNotNull(thisItem);
        
        //Now try to get something that wasn't there and test to make sure
        //that this is null
        thisItem = service.getItem("Something Else");
        assertNull(thisItem);
    }
    
    /**
     * Test of getChange method, of class VendingMachineService.
     */
    @Test
    public void testGetChange() throws Exception {
        //Create new Item with all fields filled in
        Item item = new Item();
        item.setName("Cheese Crackers");
        item.setPrice(BigDecimal.valueOf(5));
        item.setInventory(8);
        
        //Create BigDecimal for amount user put in
        BigDecimal userInput = new BigDecimal("3.00");
        
        //Get Map of change values
        Map<Coin, BigDecimal> change = service.getChange(userInput, item.getPrice());
        
        //Make sure number of quarters in change is equal
        Coin coinQ = Coin.QUARTER;
        BigDecimal numQ = new BigDecimal("-8");
        assertEquals(numQ, change.get(coinQ));
    }

    /**
     * Test of updateInventory method, of class VendingMachineService.
     */
    @Test
    public void testUpdateInventory() throws Exception {
        //Get item from testing memory
        Item item = service.getItem("Dasani");
        
        //Change inventory level
        service.updateInventory(item);
    }

    /**
     * Test of writeAuditLog method, of class VendingMachineService.
     */
    @Test
    public void testWriteAuditLog() throws Exception {
        //Do nothing
    }

    /**
     * Test of loadVendingMachine method, of class VendingMachineService.
     */
    @Test
    public void testLoadVendingMachine() throws Exception {
        //Do nothing
    }

    /**
     * Test of writeVendingMachine method, of class VendingMachineService.
     */
    @Test
    public void testWriteVendingMachine() throws Exception {
        //Do nothing
    }
    
    /**
     * Test of removeItem method, of class VendingMachineService.
     */
    @Test
    public void testRemoveItem() throws Exception {
        //Get the mp3 info from the stub file and make sure it's not null
        Item thisItem = service.getItem("Dasani");
        assertNotNull(thisItem);
        
        //Now try to delete something that wasn't there and test to make sure
        //that this is null
        thisItem = service.removeItem("Something Else");
        assertNull(thisItem);
    }
    
    /**
     * Test of addItem method, of class VendingMachineService.
     */
    @Test
    public void testAddItem() throws Exception {
        //Create new Item with all fields filled in
        Item item1 = new Item();
        item1.setName("Dasani");
        item1.setPrice(BigDecimal.TEN);
        item1.setInventory(10);
        
        //Add it to memory
        service.addItem(item1);
    }
}
