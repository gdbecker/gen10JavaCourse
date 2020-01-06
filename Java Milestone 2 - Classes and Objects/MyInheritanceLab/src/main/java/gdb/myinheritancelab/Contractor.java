package gdb.myinheritancelab;

/**
 * @date Tuesday December 10, 2019
 * @author Garrett Becker
 */

public class Contractor extends Person {
    private boolean permanent;
    private double hourlyWage;
    
    public Contractor(String name, int age, Address address, boolean permanent, double hourlyWage) {
        super(name, age, address);
        this.permanent = permanent;
        this.hourlyWage = hourlyWage;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
}
