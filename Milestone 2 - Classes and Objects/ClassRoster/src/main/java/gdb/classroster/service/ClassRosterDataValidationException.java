package gdb.classroster.service;

/**
 * @date Monday December 16, 2019
 * @author garrettbecker
 */

public class ClassRosterDataValidationException extends Exception {
    public ClassRosterDataValidationException(String message) {
        super(message);
    }
    
    public ClassRosterDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
