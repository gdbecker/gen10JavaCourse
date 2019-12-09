package gdb.classmodelingexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Model a car as if the class were to be part of an inventory system for a car dealership.
public class Car1 {
    private String carMake;
    private String carModel;
    private String carDealership;
    private double sellingPrice;

    //Constructor
    public Car1(String carMake, String carModel, String carDealership, double sellingPrice) {    
        this.carMake = carMake;
        this.carModel = carModel;
        this.carDealership = carDealership;
        this.sellingPrice = sellingPrice;
    }

    //Getters and setters
    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarDealership() {
        return carDealership;
    }

    public void setCarDealership(String carDealership) {
        this.carDealership = carDealership;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    
    //Behavior methods
    public void sellCar() {
        
    }
    
    public void stockInventory() {
        
    }
}
