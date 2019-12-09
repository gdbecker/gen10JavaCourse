package gdb.simplecalculatorexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Main Java file for Simple Calculator application
import java.util.*;
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("---- Welcome to your Simple Calculator ----");
        String operation = "";
        int operand1 = 0;
        int operand2 = 0;
        SimpleCalculator calculator = new SimpleCalculator();
        
        //Stay in the application until user decides to exit with an "e"
        while (!operation.equals("e")) {
            System.out.println("\n----");
            System.out.println("Which operation would you like to do?");
            System.out.println("Addition (+)");
            System.out.println("Subtraction (-)");
            System.out.println("Multipication (*)");
            System.out.println("Division (/)");
            System.out.println("Exit Simple Calculator (e)");
            System.out.println("Your chosen operation: ");
            operation = sc.nextLine();
            
            //Leave program loop when "e" is inputted
            if (operation.equals("e")) {
                break;
            }
            
            //Collect operands from the user
            System.out.println("Your first operand: ");
            operand1 = sc.nextInt();
            System.out.println("Your second operand: ");
            operand2 = sc.nextInt();
            
            //Run the operation depending on user input
            //If addition:
            if (operation.equals("+")) {
                System.out.println("Result: " + calculator.add(operand1, operand2));
            }
            
            //If subtraction
            else if (operation.equals("-")) {
                System.out.println("Result: " + calculator.subtract(operand1, operand2));
            }
            
            //If multipication
            else if (operation.equals("*")) {
                System.out.println("Result: " + calculator.multiply(operand1, operand2));
            }
            
            //If division
            else if (operation.equals("/")) {
                System.out.println("Result: " + calculator.divide(operand1, operand2));
            }
            
            sc.nextLine(); //Ensure that user can input a new operation if so desired
            operation = ""; //Ensure that operation variable is blank for next iteration
        }
        
        //Display thank you message only if user decides to exit the program
        if (operation.equals("e")) {
            System.out.println("----");
            System.out.println("Thank you for using your Simple Calculator!");
        }
    }
}
