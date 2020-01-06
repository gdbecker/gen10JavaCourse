package gdb.flooringmastery.dao;

/**
 * Final Project OOP
 * @author garrettbecker
 */

public interface FlooringMasteryAuditDao {
    public void writeAuditEntry(String entry) throws FlooringMasteryPersistenceException;
}
