package gdb.classmodelingexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Model a house as if the class were to be part of a GPS mapping system
public class House1 {
    private double latitude;
    private double longitude;
    private String country;
    private String city;

    //Constructor
    public House1(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    //Getters and setters
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    //Behavior method
    public String findRadioFrequency() {
        return "";
    }
}
