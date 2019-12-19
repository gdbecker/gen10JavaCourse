package gdb.mp3library.dao;

/**
 * Milestone 2 Assessment
 * @author garrettbecker
 */

public class mp3libraryPersistenceException extends Exception {
    public mp3libraryPersistenceException(String message) {
        super(message);
    }
    
    public mp3libraryPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
