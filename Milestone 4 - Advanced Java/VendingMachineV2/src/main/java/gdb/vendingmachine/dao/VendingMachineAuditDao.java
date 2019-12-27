package gdb.vendingmachine.dao;

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

public interface VendingMachineAuditDao {
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
