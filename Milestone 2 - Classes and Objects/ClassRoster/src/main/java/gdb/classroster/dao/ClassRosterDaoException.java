package gdb.classroster.dao;

/**
 * @date Thursday December 12, 2019
 * @author garrettbecker
 */

public class ClassRosterDaoException extends Exception {
    public ClassRosterDaoException(String message) {
        super(message);
    }
    
    public ClassRosterDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
