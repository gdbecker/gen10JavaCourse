package gdb.flooringmastery.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Final Project OOP
 * @author garrettbecker
 */
public class FlooringMasteryAuditDaoFileImpl implements FlooringMasteryAuditDao {

    public static final String AUDIT_FILE = "AuditLog.txt";
    
    @Override
    public void writeAuditEntry(String entry) throws FlooringMasteryPersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not persist audit information.", e);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}