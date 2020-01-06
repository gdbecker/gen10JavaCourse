package gdb.classmodelingexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Model a book as if the class were to be part of a library cataloging system.
public class Book2 {
    private String libraryName;
    private String genre;
    private double catalogCode;
    private String authorLastName;

    //Constructor
    public Book2(String genre, double catalogCode, String authorLastName) {
        this.genre = genre;
        this.catalogCode = catalogCode;
        this.authorLastName = authorLastName;
    }

    //Getters and setters
    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getCatalogCode() {
        return catalogCode;
    }

    public void setCatalogCode(double catalogCode) {
        this.catalogCode = catalogCode;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }
    
    //Behavior methods
    public void checkOutBook() {
        
    }
    
    public void returnBook() {
        
    }
}
