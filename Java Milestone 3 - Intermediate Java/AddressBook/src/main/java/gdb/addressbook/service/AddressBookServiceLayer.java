package gdb.addressbook.service;

import gdb.addressbook.dao.AddressBookPersistenceException;
import gdb.addressbook.dto.Address;
import java.util.List;

/**
 * @date Tuesday December 17, 2019
 * @author garrettbecker
 */

public interface AddressBookServiceLayer {
    void createAddress(Address address) throws
            AddressBookDuplicateLastNameException,
            AddressBookDataValidationException,
            AddressBookPersistenceException;
    
    Address removeAddress(String lastName) throws
            AddressBookPersistenceException;
    
    Address getAddress(String lastName) throws
            AddressBookPersistenceException;
    
    List<Address> getAllAddresses() throws
            AddressBookPersistenceException;
    
    String getNumAddresses() throws
            AddressBookPersistenceException;
    
    void editAddress(Address address) throws
            AddressBookDuplicateLastNameException,
            AddressBookDataValidationException,
            AddressBookPersistenceException;
    
    void loadAddressBook() throws AddressBookPersistenceException;
    
    void writeAddressBook() throws AddressBookPersistenceException;
}
