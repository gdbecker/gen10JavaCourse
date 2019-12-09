package gdb.classmodelingexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Model an airplane as if the class were to be part of an air traffic control system.
public class Airplane1 {
    private String departureCity;
    private String destinationCity;
    private String departureRunwayCode;
    private String destinationRunwayCode;
    private double velocity;

    //Constructor
    public Airplane1(String departureCity, String destinationCity, String departureRunwayCode, String destinationRunwayCode) {
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureRunwayCode = departureRunwayCode;
        this.destinationRunwayCode = destinationRunwayCode;
    }
    
    //Getters and setters
    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureRunwayCode() {
        return departureRunwayCode;
    }

    public void setDepartureRunwayCode(String departureRunwayCode) {
        this.departureRunwayCode = departureRunwayCode;
    }

    public String getDestinationRunwayCode() {
        return destinationRunwayCode;
    }

    public void setDestinationRunwayCode(String destinationRunwayCode) {
        this.destinationRunwayCode = destinationRunwayCode;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    
    //Behavior method
    public void initiateLanding() {
        
    }
}
