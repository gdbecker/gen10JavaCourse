package gdb.classmodelingexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Model a book as if the class were to be part of a publishing system that the author uses to write the book.
public class Book1 {
    private String publishingHouse;
    private String authorLastName;
    private double sellingPrice;
    private double productionCost;

    //Constructor
    public Book1(String publishingHouse, String authorLastName, double sellingPrice) {
        this.publishingHouse = publishingHouse;
        this.authorLastName = authorLastName;
        this.sellingPrice = sellingPrice;
    }
    
    //Getters and setters
    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(double productionCost) {
        this.productionCost = productionCost;
    }
    
    //Behavior methods
    public void printBook() {
        
    }
    
    public void editBook() {
        
    }
}
