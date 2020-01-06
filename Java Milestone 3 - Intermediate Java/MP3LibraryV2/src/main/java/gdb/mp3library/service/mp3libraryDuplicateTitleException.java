package gdb.mp3library.service;

/**
 * @date Wednesday December 18, 2019
 * @author garrettbecker
 */

public class mp3libraryDuplicateTitleException extends Exception {
    public mp3libraryDuplicateTitleException(String message) {
        super(message);
    }
    
    public mp3libraryDuplicateTitleException(String message, Throwable cause) {
        super(message, cause);
    }
}
