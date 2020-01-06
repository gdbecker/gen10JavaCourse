package gdb.vendingmachine.service;

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

public class VendingMachineInsufficientFundsException extends Exception {
    public VendingMachineInsufficientFundsException(String message) {
        super(message);
    }
    
    public VendingMachineInsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
