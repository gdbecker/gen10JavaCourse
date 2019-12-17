package gdb.addressbook.dao;

/**
 * @date Thursday December 12, 2019
 * @author garrettbecker
 */

public class AddressBookDaoException extends Exception {
    public AddressBookDaoException(String message) {
        super(message);
    }
    
    public AddressBookDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
