package gdb.classroster.service;

/**
 * @date Monday December 16, 2019
 * @author garrettbecker
 */

public class ClassRosterDuplicateIdException extends Exception {
    public ClassRosterDuplicateIdException(String message) {
        super(message);
    }
    
    public ClassRosterDuplicateIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
