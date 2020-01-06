package gdb.addressbook.service;

import gdb.addressbook.dao.AddressBookDao;
import gdb.addressbook.dao.AddressBookPersistenceException;
import gdb.addressbook.dto.Address;
import java.util.List;

/**
 * @date Tuesday December 17, 2019
 * @author garrettbecker
 */

public class AddressBookServiceLayerImpl implements AddressBookServiceLayer {
    
    AddressBookDao dao;
    
    //Constructor
    public AddressBookServiceLayerImpl(AddressBookDao dao) {
        this.dao = dao;
    }
    
    public void validateAddressData(Address address) throws AddressBookDataValidationException {
        if (address.getFirstName() == null
                || address.getFirstName().trim().length() == 0
                || address.getLastName() == null
                || address.getLastName().trim().length() == 0
                || address.getStreet() == null
                || address.getStreet().trim().length() == 0
                || address.getCity() == null
                || address.getCity().trim().length() == 0
                || address.getState() == null
                || address.getState().trim().length() == 0
                || address.getZip() == null
                || address.getZip().trim().length() == 0) {
            throw new AddressBookDataValidationException("ERROR: All fields are required.");
        }
    }

    @Override
    public void createAddress(Address address) throws AddressBookDuplicateLastNameException, AddressBookDataValidationException, AddressBookPersistenceException {
        //Check first to make sure there aren't any duplicates of address info
        if(dao.getAddress(address.getLastName()) != null) {
            throw new AddressBookDuplicateLastNameException("ERROR: Could not create Address. An address with Last Name " + address.getLastName() + " already exists.");
        }
        
        //Validate all fields of the Address object being inputted
        validateAddressData(address);
        
        //Passed the business rules checks. Now add this address to the system
        dao.addAddress(address.getLastName(), address);
    }

    @Override
    public Address removeAddress(String lastName) throws AddressBookPersistenceException {
        Address removedAddress = dao.removeAddress(lastName);
        return removedAddress;
    }

    @Override
    public Address getAddress(String lastName) throws AddressBookPersistenceException {
        return dao.getAddress(lastName); //pass through
    }

    @Override
    public List<Address> getAllAddresses() throws AddressBookPersistenceException {
        return dao.getAllAddresses(); //pass through
    }

    @Override
    public String getNumAddresses() throws AddressBookPersistenceException {
        return dao.getNumAddresses();
    }

    @Override
    public void editAddress(Address address) throws AddressBookDuplicateLastNameException, AddressBookDataValidationException, AddressBookPersistenceException {
        //Check first to make sure there aren't any duplicates of address info
        //if(dao.getAddress(address.getLastName()) != null) {
        //    throw new AddressBookDuplicateLastNameException("ERROR: Could not create Address. An address with Last Name " + address.getLastName() + " already exists.");
        //}
        
        //Validate all fields of the Address object being inputted
        validateAddressData(address);
        
        //Passed the business rules checks. Now add this address to the system
        dao.editAddress(address.getLastName(), address);
    }

    @Override
    public void loadAddressBook() throws AddressBookPersistenceException {
        dao.loadAddressBook();
    }

    @Override
    public void writeAddressBook() throws AddressBookPersistenceException {
        dao.writeAddressBook();
    }
    
}
