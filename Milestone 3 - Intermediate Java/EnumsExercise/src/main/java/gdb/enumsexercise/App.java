package gdb.enumsexercise;

/**
 * @date Wednesday December 18, 2019
 * @author garrettbecker
 * 
 * Exercise 1: How Many Days Until Friday
 * Create an enum for every day of the week,
 * Create an App class that asks the user to enter a day of the week 
 * and then use a switch statement and your enum to print out how many days 
 * there are until Friday.
 * 
 * Exercise 2: Math Operators
 * Create an enum for math Operators: plus, minus, multiply, and divide.
 * Create an App class that reads in two operands and calls the calculate method 
 * (shown below in course page) one time for each operator in your enum.
 * Output your results to the screen after every operation is performed.
 */

import java.util.*;

enum WeekDays {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

enum MathOperators {
    PLUS, MINUS, MULTIPLY, DIVIDE 
}

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //Exercise 1: Days of the Week
        WeekDays day = WeekDays.MONDAY;
        System.out.println("Enter a day of the week:");
        String choice = sc.nextLine();
        
        //Get enum option based on user input
        if (choice.equalsIgnoreCase("Monday")) {
            day = WeekDays.MONDAY;
        } 
         
        else if (choice.equalsIgnoreCase("Tuesday")) {
            day = WeekDays.TUESDAY;
        }
        
        else if (choice.equalsIgnoreCase("Wednesday")) {
            day = WeekDays.WEDNESDAY;
        }
        
        else if (choice.equalsIgnoreCase("Thursday")) {
            day = WeekDays.THURSDAY;
        }
        
        else if (choice.equalsIgnoreCase("Friday")) {
            day = WeekDays.FRIDAY;
        }
        
        else if (choice.equalsIgnoreCase("Saturday")) {
            day = WeekDays.SATURDAY;
        }
        
        else if (choice.equalsIgnoreCase("Sunday")) {
            day = WeekDays.SUNDAY;
        }
        
        switch (day) {
            case MONDAY:
                System.out.println("There are 4 days until Friday.");
                System.out.println("");
                break;
            case TUESDAY:
                System.out.println("There are 3 days until Friday!");
                System.out.println("");
                break;
            case WEDNESDAY:
                System.out.println("There are 2 days until Friday!");
                System.out.println("");
                break;
            case THURSDAY:
                System.out.println("There are 1 day until Friday!");
                System.out.println("");
                break;
            case FRIDAY:
                System.out.println("TODAY IS FRIDAY!");
                System.out.println("");
                break;
            case SATURDAY:
                System.out.println("There are 6 days until Friday.");
                System.out.println("");
                break;
            case SUNDAY:
                System.out.println("There are 5 days until Friday."); 
                System.out.println("");
                break;
            default:
                System.out.println("Unknown Command");
        }
        
        //Exercise 2: math operations
        IntMath myIntMath = new IntMath();
        System.out.println("Please enter two numbers to do the four main operations:");
        System.out.print("First number: ");
        int num1 = sc.nextInt();
        System.out.print("Second number: ");
        int num2 = sc.nextInt();
        
        System.out.println("\nADDITION: ");
        int result = myIntMath.calculate(MathOperators.PLUS, num1, num2);
        System.out.println(num1 + " + " + num2 + " = " + result);
        
        System.out.println("\nSUBTRACTION: ");
        result = myIntMath.calculate(MathOperators.MINUS, num1, num2);
        System.out.println(num1 + " - " + num2 + " = " + result);
        
        System.out.println("\nMULTIPICATION: ");
        result = myIntMath.calculate(MathOperators.MULTIPLY, num1, num2);
        System.out.println(num1 + " * " + num2 + " = " + result);
        
        System.out.println("\nDIVISION: ");
        result = myIntMath.calculate(MathOperators.DIVIDE, num1, num2);
        System.out.println(num1 + " / " + num2 + " = " + result);
    }
}
