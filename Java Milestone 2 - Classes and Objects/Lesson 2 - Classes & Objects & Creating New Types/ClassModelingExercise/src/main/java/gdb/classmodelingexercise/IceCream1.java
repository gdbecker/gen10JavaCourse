package gdb.classmodelingexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Model ice cream as if the class were to be part of the control system at the dairy that makes the ice cream.
public class IceCream1 {
    private String manufacturer;
    private String flavor;
    private String productionQuantity;
    private String packagingSize;
    private String sku;

    //Constructor
    public IceCream1(String manufacturer, String productionQuantity, String packagingSize, String sku) {    
        this.manufacturer = manufacturer;
        this.productionQuantity = productionQuantity;
        this.packagingSize = packagingSize;
        this.sku = sku;
    }

    //Getters and setters
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getProductionQuantity() {
        return productionQuantity;
    }

    public void setProductionQuantity(String productionQuantity) {
        this.productionQuantity = productionQuantity;
    }

    public String getPackagingSize() {
        return packagingSize;
    }

    public void setPackagingSize(String packagingSize) {
        this.packagingSize = packagingSize;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    //Behavior method
    public void startProduction() {
        
    }
    
    public void loadPackageCartons() {
        
    }
    
}
