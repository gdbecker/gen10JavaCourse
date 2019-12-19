package gdb.mp3library.service;

/**
 * @date Wednesday December 18, 2019
 * @author garrettbecker
 */

public class mp3libraryDataValidationException extends Exception {
    public mp3libraryDataValidationException(String message) {
        super(message);
    }
    
    public mp3libraryDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
