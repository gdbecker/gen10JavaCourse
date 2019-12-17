package gdb.addressbook.service;

/**
 * @date Tuesday December 17, 2019
 * @author garrettbecker
 */

public class AddressBookDataValidationException extends Exception {
    public AddressBookDataValidationException(String message) {
        super(message);
    }
    
    public AddressBookDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
