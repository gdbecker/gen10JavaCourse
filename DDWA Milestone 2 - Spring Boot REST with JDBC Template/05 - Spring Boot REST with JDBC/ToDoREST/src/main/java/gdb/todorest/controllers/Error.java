package gdb.todorest.controllers;

import java.time.LocalDateTime;

/**
 * @date Thursday January 16, 2020
 * @author garrettbecker
 */

public class Error {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
