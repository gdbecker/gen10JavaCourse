package gdb.userioclassexercise;

/**
 * @date Tuesday December 10, 2019
 * @author Garrett Becker
 */

import java.util.*;
public class AppSimpleCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        App myApp = new App();
        
        myApp.print("---- Welcome to your Simple Calculator ----");
        String operation = "";
        int operand1 = 0;
        int operand2 = 0;
        SimpleCalculator calculator = new SimpleCalculator();
        
        //Stay in the application until user decides to exit with an "e"
        while (!operation.equals("e")) {
            myApp.print("\n----");
            myApp.print("Which operation would you like to do?");
            myApp.print("Addition (+)");
            myApp.print("Subtraction (-)");
            myApp.print("Multipication (*)");
            myApp.print("Division (/)");
            myApp.print("Exit Simple Calculator (e)");
            operation = myApp.readString("Your chosen operation: ");
            
            //Leave program loop when "e" is inputted
            if (operation.equals("e")) {
                break;
            }
            
            //Collect operands from the user
            operand1 = myApp.readInt("Your first operand: ");
            operand2 = myApp.readInt("Your second operand: ");
            
            //Run the operation depending on user input
            //If addition:
            if (operation.equals("+")) {
                System.out.println(operand1 + " " + operation + " " + operand2 + " = " + calculator.add(operand1, operand2));
            }
            
            //If subtraction
            else if (operation.equals("-")) {
                System.out.println(operand1 + " " + operation + " " + operand2 + " = " + calculator.subtract(operand1, operand2));
            }
            
            //If multipication
            else if (operation.equals("*")) {
                System.out.println(operand1 + " " + operation + " " + operand2 + " = " + calculator.multiply(operand1, operand2));
            }
            
            //If division
            else if (operation.equals("/")) {
                System.out.println(operand1 + " " + operation + " " + operand2 + " = " + calculator.divide(operand1, operand2));
            }
            
            sc.nextLine(); //Ensure that user can input a new operation if so desired
            operation = ""; //Ensure that operation variable is blank for next iteration
        }
        
        //Display thank you message only if user decides to exit the program
        if (operation.equals("e")) {
            myApp.print("----");
            myApp.print("Thank you for using your Simple Calculator!");
        }
    }
}
