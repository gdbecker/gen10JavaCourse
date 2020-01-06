package gdb.classroster.dao;

/**
 * @date Thursday December 12, 2019
 * Monday December 16, 2019
 * @author garrettbecker
 */

public class ClassRosterPersistenceException extends Exception {
    public ClassRosterPersistenceException(String message) {
        super(message);
    }
    
    public ClassRosterPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
