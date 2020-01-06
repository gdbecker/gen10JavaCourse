package gdb.myinheritancelab;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

public class Address {
    //Attributes
    private String street;
    private String city;
    private String zip;

    //Constructor
    public Address(String street, String city, String zip) {
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
    
    //Getters
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }
}
