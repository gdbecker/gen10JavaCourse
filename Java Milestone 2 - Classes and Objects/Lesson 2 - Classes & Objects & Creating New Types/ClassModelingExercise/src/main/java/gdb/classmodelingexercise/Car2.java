package gdb.classmodelingexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Model a car as if the class were to be part of a video game.
public class Car2 {
    private String carMake;
    private String carModel;
    private String gameName;
    private String color;
    private String speed;

    //Constructor
    public Car2(String carMake, String carModel, String gameName, String color) {
        this.carMake = carMake;
        this.carModel = carModel;
        this.gameName = gameName;
        this.color = color;
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

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
    
    //Behavior methods
    public void selectCar() {
        
    }
    
    public void playCar() {
        
    }
}
