package gdb.objectinstantiation;

/**
 * Lesson 02 Video
 * @author Garrett Becker
 */

//This is the kickoff/entry point into your application
//Make this a standard convention to call the main Java file "App"
public class App {
    public static void main(String[] args) {
        
        double myPi = Adder.PI;
        
        int sum = Adder.add(5, 4);
        
        System.out.println("The sum is " + sum);
    }
}