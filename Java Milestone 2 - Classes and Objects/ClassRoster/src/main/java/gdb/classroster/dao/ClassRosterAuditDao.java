package gdb.classroster.dao;

/**
 * @date Monday December 16, 2019 
 * @author garrettbecker
 */

public interface ClassRosterAuditDao {
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;
}
