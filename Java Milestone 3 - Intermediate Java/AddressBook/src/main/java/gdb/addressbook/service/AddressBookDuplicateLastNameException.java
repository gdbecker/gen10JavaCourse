package gdb.addressbook.service;

/**
 * @date Tuesday December 17, 2019
 * @author garrettbecker
 */

public class AddressBookDuplicateLastNameException extends Exception {
    public AddressBookDuplicateLastNameException(String message) {
        super(message);
    }
    
    public AddressBookDuplicateLastNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
