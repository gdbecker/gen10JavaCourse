package gdb.myinheritancelab;

/**
 * @date Tuesday December 10, 2019
 * @author Garrett Becker
 */

public class Employee extends Person {
    protected double salary;
    
    public Employee(String name, int age, Address address, double salary) {
        super(name, age, address);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "name=" + name + ", age=" + age + ", address=" + address + ", salary=" + salary + '}';
    }
    
    
}
