package gdb.vendingmachine.dao;

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

public class VendingMachinePersistenceException extends Exception {
    public VendingMachinePersistenceException(String message) {
        super(message);
    }
    
    public VendingMachinePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
