package gdb.addressbook.dao;

import gdb.addressbook.dto.Address;
import java.util.List;

/**
 * @date Thursday December 12, 2019
 * @author garrettbecker
 */

public interface AddressBookDao {
    /**
     * Adds the given Address to the address book and associates it with the given 
     * Last Name. If there is already an address associated with the given 
     * Last Name it will return that Address object, otherwise it will 
     * return null.
     * 
     * @param lastName last name address is linked with
     * @param address address that needs to be added
     * @return the Address object previously associated with the given  
     * Last Name if it exists, null otherwise
     */
    Address addAddress(String lastName, Address address) throws AddressBookPersistenceException;
    
    /**
     * Removes from the address book the address associated with the given last name. 
     * Returns the address object that is being removed or null if 
     * there is no address associated with the given last name
     * 
     * @param lastName last name of address to be removed
     * @return Address object that was removed or null if no address 
     * was associated with the given last name
     */
    Address removeAddress(String lastName) throws AddressBookPersistenceException;
    
    /**
     * Returns the address object associated with the given last name.
     * Returns null if no such address exists
     * 
     * @param lastName ID of the student to retrieve
     * @return the Address object associated with the given last name,  
     * null if no such address exists
     */
    Address getAddress(String lastName) throws AddressBookPersistenceException;
    
    /**
     * Returns a String array containing the addresses in the address book
     * 
     * @return String array containing all of the addresses in the book
     */
    List<Address> getAllAddresses() throws AddressBookPersistenceException;
    
    /**
     * Returns an int representing the number of addresses in the address book
     * 
     * @return int representing total number of addresses in address book
     */
    String getNumAddresses() throws AddressBookPersistenceException;
    
    /**
     * Returns the edited version of the address object associated with 
     * the given last name
     * 
     * @param lastName last name of address to be edited
     * @return Address object that was edit or null if no address 
     * was associated with the given last name
     */
    Address editAddress(String lastName, Address address) throws AddressBookPersistenceException;
    
    /**
     * Loads the existing file with address book info into memory
     */
    void loadAddressBook() throws AddressBookPersistenceException;
    
    /**
     * Writes the address book data in memory to the existing file
     */
    void writeAddressBook() throws AddressBookPersistenceException;
}
