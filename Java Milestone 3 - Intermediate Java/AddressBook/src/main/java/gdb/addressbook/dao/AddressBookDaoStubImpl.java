package gdb.addressbook.dao;

import gdb.addressbook.dto.Address;
import java.util.ArrayList;
import java.util.List;

/**
 * @date Tuesday December 17, 2019
 * @author garrettbecker
 */

//This is basically a dumbed down version of the AddressBookDaoFileImpl file
//This exists for the purpose of testing that these methods work
public class AddressBookDaoStubImpl implements AddressBookDao {
    
    Address onlyAddress;
    List<Address> addressList = new ArrayList<>();
    
    public AddressBookDaoStubImpl() {
        onlyAddress = new Address("Smith");
        onlyAddress.setFirstName("Joe");
        onlyAddress.setStreet("2837 Farmers Hill St");
        onlyAddress.setCity("Charlotte");
        onlyAddress.setState("NC");
        onlyAddress.setZip("92837");
        
        addressList.add(onlyAddress);
    }

    @Override
    public Address addAddress(String lastName, Address address) throws AddressBookPersistenceException {
        //Making sure that only one Address with the lastName will exist in memory
        //We don't want duplicate objects with the same lastName
        if (lastName.equals(onlyAddress.getLastName())) {
            return onlyAddress;
        } else {
            return null;
        }
    }

    @Override
    public Address removeAddress(String lastName) throws AddressBookPersistenceException {
        //Making sure that only one Address with the lastName will exist in memory
        //We don't want duplicate objects with the same lastName
        if (lastName.equals(onlyAddress.getLastName())) {
            return onlyAddress;
        } else {
            return null;
        }
    }

    @Override
    public Address getAddress(String lastName) throws AddressBookPersistenceException {
        //Making sure that only one Address with the lastName will exist in memory
        //We don't want duplicate objects with the same lastName
        if (lastName.equals(onlyAddress.getLastName())) {
            return onlyAddress;
        } else {
            return null;
        }
    }

    @Override
    public List<Address> getAllAddresses() throws AddressBookPersistenceException {
        return addressList;
    }

    @Override
    public String getNumAddresses() throws AddressBookPersistenceException {
        return "There are " + addressList.size() + " addresses in the book.";
    }

    @Override
    public Address editAddress(String lastName, Address address) throws AddressBookPersistenceException {
        //Making sure that only one Address with the lastName will exist in memory
        //We don't want duplicate objects with the same lastName
        if (lastName.equals(onlyAddress.getLastName())) {
            return onlyAddress;
        } else {
            return null;
        }
    }

    @Override
    public void loadAddressBook() throws AddressBookPersistenceException {
        //Do nothing
    }

    @Override
    public void writeAddressBook() throws AddressBookPersistenceException {
        //Do nothing
    }
    
}
