package gdb.classmodelingexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Model ice cream as if the class were to be part of the stocking system at a grocery store.
public class IceCream2 {
    private String sku;
    private String groceryStore;
    private int aisleNumber;
    private int quantityInStock;
    private int amountToOrder;

    //Constructor
    public IceCream2(String sku, String groceryStore, int aisleNumber, int quantityInStock, int amountToOrder) {    
        this.sku = sku;
        this.groceryStore = groceryStore;
        this.aisleNumber = aisleNumber;
        this.quantityInStock = quantityInStock;
        this.amountToOrder = amountToOrder;
    }

    //Getters and setters
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getGroceryStore() {
        return groceryStore;
    }

    public void setGroceryStore(String groceryStore) {
        this.groceryStore = groceryStore;
    }

    public int getAisleNumber() {
        return aisleNumber;
    }

    public void setAisleNumber(int aisleNumber) {
        this.aisleNumber = aisleNumber;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public int getAmountToOrder() {
        return amountToOrder;
    }

    public void setAmountToOrder(int amountToOrder) {
        this.amountToOrder = amountToOrder;
    }
    
    //Behavior method
    public void stock() {
        
    }
    
    public void order() {
        
    }
    
}
