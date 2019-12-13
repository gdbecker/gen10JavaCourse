package gdb.mp3library.dao;

/**
 * Milestone 2 Assessment
 * @author garrettbecker
 */

public class mp3libraryDaoException extends Exception {
    public mp3libraryDaoException(String message) {
        super(message);
    }
    
    public mp3libraryDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
