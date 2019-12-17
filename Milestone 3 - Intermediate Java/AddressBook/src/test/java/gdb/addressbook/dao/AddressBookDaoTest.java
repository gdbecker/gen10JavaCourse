/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdb.addressbook.dao;

import gdb.addressbook.dto.Address;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @date Tuesday December 17, 2019
 * @author garrettbecker
 */

public class AddressBookDaoTest {
    
    private AddressBookDao dao = new AddressBookDaoFileImpl();
    
    public AddressBookDaoTest() {
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
        List<Address> listAddress = dao.getAllAddresses();
        for (Address a : listAddress) {
            dao.removeAddress(a.getLastName());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addAddress method, of class AddressBookDao.
     */
    @Test //Add/Get/Edit tests are identical
    public void testAddAddress() throws Exception {
        //Make up a random Address object with all fields filled in
        Address address = new Address("Smith");
        address.setFirstName("Joe");
        address.setStreet("1829 Hickory Lane");
        address.setCity("Charlotte");
        address.setState("NC");
        address.setZip("28374");
        
        //Add this random Address to memory
        dao.addAddress(address.getLastName(), address);
        
        //Now try pulling up the Address that should have been put into memory
        Address fromDao = dao.getAddress(address.getLastName());
        
        //Make sure that these objects are the same 
        assertEquals(address, fromDao);
    }

    /**
     * Test of removeAddress method, of class AddressBookDao.
     */
    @Test
    public void testRemoveAddress() throws Exception {
        //Make up a random Address object with all fields filled in
        Address address1 = new Address("Smith");
        address1.setFirstName("Joe");
        address1.setStreet("1829 Hickory Lane");
        address1.setCity("Charlotte");
        address1.setState("NC");
        address1.setZip("28374");
        
        //Add it to memory
        dao.addAddress(address1.getLastName(), address1);
        
        //Make up another random Address object with all fields filled in
        Address address2 = new Address("Allen");
        address2.setFirstName("Barry");
        address2.setStreet("1928 Flash Place");
        address2.setCity("Central City");
        address2.setState("WI");
        address2.setZip("82736");
        
        //Add it to memory
        dao.addAddress(address2.getLastName(), address2);
        
        //Now test to make sure that removing each Address actually works
        dao.removeAddress(address1.getLastName());
        assertEquals(1, dao.getAllAddresses().size());
        assertNull(dao.getAddress(address1.getLastName()));
        
        dao.removeAddress(address2.getLastName());
        assertEquals(0, dao.getAllAddresses().size());
        assertNull(dao.getAddress(address2.getLastName()));
    }

    /**
     * Test of getAddress method, of class AddressBookDao.
     */
    @Test //Add/Get/Edit tests are identical
    public void testGetAddress() throws Exception {
        //Make up a random Address object with all fields filled in
        Address address = new Address("Smith");
        address.setFirstName("Joe");
        address.setStreet("1829 Hickory Lane");
        address.setCity("Charlotte");
        address.setState("NC");
        address.setZip("28374");
        
        //Add this random Address to memory
        dao.addAddress(address.getLastName(), address);
        
        //Now try pulling up the Address that should have been put into memory
        Address fromDao = dao.getAddress(address.getLastName());
        
        //Make sure that these objects are the same 
        assertEquals(address, fromDao);
    }

    /**
     * Test of getAllAddresses method, of class AddressBookDao.
     */
    @Test
    public void testGetAllAddresses() throws Exception {
        //Make up a random Address object with all fields filled in
        Address address1 = new Address("Smith");
        address1.setFirstName("Joe");
        address1.setStreet("1829 Hickory Lane");
        address1.setCity("Charlotte");
        address1.setState("NC");
        address1.setZip("28374");
        
        //Add it to memory
        dao.addAddress(address1.getLastName(), address1);
        
        //Make up another random Address object with all fields filled in
        Address address2 = new Address("Allen");
        address2.setFirstName("Barry");
        address2.setStreet("1928 Flash Place");
        address2.setCity("Central City");
        address2.setState("WI");
        address2.setZip("82736");
        
        //Add it to memory
        dao.addAddress(address2.getLastName(), address2);
        
        //Make sure that there are two Addresses in there
        assertEquals(2, dao.getAllAddresses().size());
    }

    /**
     * Test of getNumAddresses method, of class AddressBookDao.
     */
    @Test
    public void testGetNumAddresses() throws Exception {
        //Make up a random Address object with all fields filled in
        Address address1 = new Address("Smith");
        address1.setFirstName("Joe");
        address1.setStreet("1829 Hickory Lane");
        address1.setCity("Charlotte");
        address1.setState("NC");
        address1.setZip("28374");
        
        //Add it to memory
        dao.addAddress(address1.getLastName(), address1);
        
        //Make up another random Address object with all fields filled in
        Address address2 = new Address("Allen");
        address2.setFirstName("Barry");
        address2.setStreet("1928 Flash Place");
        address2.setCity("Central City");
        address2.setState("WI");
        address2.setZip("82736");
        
        //Add it to memory
        dao.addAddress(address2.getLastName(), address2);
        
        //Make sure that there are two Addresses in there
        assertEquals(2, dao.getAllAddresses().size());
    }

    /**
     * Test of editAddress method, of class AddressBookDao.
     */
    @Test //Add/Get/Edit tests are identical
    public void testEditAddress() throws Exception {
        //Make up a random Address object with all fields filled in
        Address address = new Address("Smith");
        address.setFirstName("Joe");
        address.setStreet("1829 Hickory Lane");
        address.setCity("Charlotte");
        address.setState("NC");
        address.setZip("28374");
        
        //Add this random Address to memory
        dao.editAddress(address.getLastName(), address);
        
        //Now try pulling up the Address that should have been put into memory
        Address fromDao = dao.getAddress(address.getLastName());
        
        //Make sure that these objects are the same 
        assertEquals(address, fromDao);
    }

    /**
     * Test of loadAddressBook method, of class AddressBookDao.
     */
    @Test
    public void testLoadAddressBook() throws Exception {
    }

    /**
     * Test of writeAddressBook method, of class AddressBookDao.
     */
    @Test
    public void testWriteAddressBook() throws Exception {
    }
    
}
