/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdb.addressbook.service;

import gdb.addressbook.dao.AddressBookDao;
import gdb.addressbook.dao.AddressBookDaoFileImpl;
import gdb.addressbook.dao.AddressBookDaoStubImpl;
import gdb.addressbook.dao.AddressBookPersistenceException;
import gdb.addressbook.dto.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @date Tuesday December 17, 2019
 * @author garrettbecker
 */

public class AddressBookServiceLayerTest {
    
    private AddressBookServiceLayer service;
    
    public AddressBookServiceLayerTest() {
        //Use the stub version since we are testing
        AddressBookDao dao = new AddressBookDaoStubImpl(); 
        
        service = new AddressBookServiceLayerImpl(dao);
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
     * Test of createAddress method, of class AddressBookServiceLayer.
     */
    @Test
    public void testCreateAddress() throws Exception {
        //Make up a random Address object with all fields filled in
        //Make sure that this one is different from the one in AddressBookDaoStubImpl
        Address address = new Address("Allen");
        address.setFirstName("Barry");
        address.setStreet("92837 Flash Place");
        address.setCity("Central City");
        address.setState("WI");
        address.setZip("82763");
        
        service.createAddress(address);
        //Remember: just checking that the business rules are working
        //We don't care about data persistence because that's what we're checking in DAO testing
    }
    
    @Test
    public void testCreateAddressDuplicateLastName() throws Exception {
        //Make a new Address with the same last name as the one in AddressBookDaoStubImpl
        Address address = new Address("Smith");
        address.setFirstName("Sarah");
        address.setStreet("92837 Hill Way");
        address.setCity("Charlotte");
        address.setState("NC");
        address.setZip("92837");
        
        try {
            service.createAddress(address);
            fail("Expected AddressBookDuplicateLastNameException was not thrown.");
        } catch (AddressBookDuplicateLastNameException e) { 
            return;
        }
    }
    
    @Test
    public void testCreateAddressInvalidData() throws Exception {
        //Make an Address object but leave a field blank
        Address address = new Address("Allen");
        address.setFirstName("");
        address.setStreet("92837 Flash Place");
        address.setCity("Central City");
        address.setState("WI");
        address.setZip("82763");
        
        try {
            service.createAddress(address);
            fail("Expected AddressBookDataValidationException was not thrown.");
        } catch (AddressBookDataValidationException e) { 
            return;
        }
    }

    /**
     * Test of removeAddress method, of class AddressBookServiceLayer.
     */
    @Test
    public void testRemoveAddress() throws Exception {
        Address address = service.getAddress("Smith");
        assertNotNull(address);
        address = service.removeAddress("Brooks");
        assertNull(address);
    }

    /**
     * Test of getAddress method, of class AddressBookServiceLayer.
     */
    @Test
    public void testGetAddress() throws Exception {
        Address address = service.getAddress("Smith");
        assertNotNull(address);
        address = service.getAddress("Brooks");
        assertNull(address);
    }

    /**
     * Test of getAllAddresses method, of class AddressBookServiceLayer.
     */
    @Test
    public void testGetAllAddresses() throws Exception {
        assertEquals(1, service.getAllAddresses().size());
    }

    /**
     * Test of getNumAddresses method, of class AddressBookServiceLayer.
     */
    @Test
    public void testGetNumAddresses() throws Exception {
        assertEquals(1, service.getAllAddresses().size());
    }

    /**
     * Test of editAddress method, of class AddressBookServiceLayer.
     */
    @Test
    public void testEditAddress() throws Exception {
        Address toEdit = service.getAddress("Smith");
        toEdit.setFirstName("Michael");
        toEdit.setState("CA");
        service.editAddress(toEdit);
    }
    
    @Test
    public void testEditAddressInvalidData() throws Exception {
        Address toEdit = service.getAddress("Smith");
        toEdit.setFirstName("Michael");
        toEdit.setState("");
        
        try {
            service.editAddress(toEdit);
            fail("Expected AddressBookDataValidationException was not thrown.");
        } catch (AddressBookDataValidationException e) { 
            return;
        }
        
    }

    /**
     * Test of loadAddressBook method, of class AddressBookServiceLayer.
     */
    @Test
    public void testLoadAddressBook() throws Exception {
        //Leave blank
    }

    /**
     * Test of writeAddressBook method, of class AddressBookServiceLayer.
     */
    @Test
    public void testWriteAddressBook() throws Exception {
        //Leave blank
    }
}
