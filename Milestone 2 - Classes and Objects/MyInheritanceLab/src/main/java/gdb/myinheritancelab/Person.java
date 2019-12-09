package gdb.myinheritancelab;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */
public class Person {
    //Attributes
    private String name;
    private int age;
    private Address address;
    
    //Main method, test driver
    public static void main(String[] args) {
        Address myAddress = new Address("2819 Hickory Lane", "Nashville", "");
        Person myPerson = new Person("Michael", 34, myAddress);
        System.out.println(myPerson.toString());
    }

    //Constructor
    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
    
    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", age=" + age + ", address=" + address + '}';
    }
    
}
