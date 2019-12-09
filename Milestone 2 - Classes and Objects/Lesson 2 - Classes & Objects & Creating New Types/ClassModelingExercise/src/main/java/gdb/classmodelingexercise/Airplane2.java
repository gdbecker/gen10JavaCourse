package gdb.classmodelingexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Model an airplane as if the class were to be part of a flight simulator.
public class Airplane2 {
    private String gameConsole;
    private String gameName;
    private double velocity;
    private double windSpeed;
    private String facingDirection;

    //Constructor
    public Airplane2(String gameConsole, String gameName, double velocity, double windSpeed) {    
        this.gameConsole = gameConsole;
        this.gameName = gameName;
        this.velocity = velocity;
        this.windSpeed = windSpeed;
    }

    //Getters and setters
    public String getGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(String gameConsole) {
        this.gameConsole = gameConsole;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(String facingDirection) {
        this.facingDirection = facingDirection;
    }
    
    //Behavior methods
    public void takeOff() {
    
    }
    
    public void land() {
        
    }
}
