package gdb.addressbook.dao;

/**
 * @date Tuesday December 17, 2019
 * @author garrettbecker
 */

public class AddressBookPersistenceException extends Exception {
    public AddressBookPersistenceException(String message) {
        super(message);
    }
    
    public AddressBookPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
