package gdb.refactorintoobjectsexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Refactor into an object
import java.util.Scanner;
public class InterestCalculator {
    public void calculate() {
        Scanner sc = new Scanner(System.in);
        
        //Gather user input to use for calculations
        System.out.println("--- Welcome to your Interest Calculator ---");
        System.out.println("Please enter the following:");
        System.out.print("Annual interest rate (as percent): ");
        double interestRate = sc.nextDouble();
        System.out.print("Initial amount ($): ");
        double initialAmt = sc.nextDouble();
        System.out.print("Number of years the money is to stay in the fund: ");
        int years = sc.nextInt();
        System.out.print("Would you like to compound yearly, quarterly, monthly, or daily? (y/q/m/d): ");
        String freq = sc.next();
        int compound = 0;
        String compoundStr = "";
        String periodLabel = "";
        double newAmt;
        
        //Get values from user input for compounding frequency
        //Get number of periods to compound per year
        //Get appropriate Strings for labeling results
        if (freq.equals("y")) {
            compound = 1;
            compoundStr = "yearly";
            periodLabel = "Year";
        } else if (freq.equals("q")) {
            compound = 4;
            compoundStr = "quarterly";
            periodLabel = "Quarter";
        } else if (freq.equals("m")) {
            compound = 12;
            compoundStr = "monthly";
            periodLabel = "Month";
        } else if (freq.equals("d")) {
            compound = 365;
            compoundStr = "daily";
            periodLabel = "Day";
        }
        
        //Get number of periods to cycle through
        long periods = years * compound;
        
        //Math and printing out results to console
        System.out.println("---------------");
        for (int i = 1; i <= periods; i++) {
            System.out.println(periodLabel + ": " + i);
            newAmt = initialAmt * Math.pow(1 + ((interestRate / 100 / compound) * 1), compound * years); //calculate interest
            
            System.out.println("Principal at Beginning of " + periodLabel + ": $" + initialAmt);
            System.out.println("Total Amount of Interest Earned: $" + (newAmt - initialAmt));
            System.out.println("Principal at End of " + periodLabel + ": $" + newAmt);
            System.out.println("\n");
            
            initialAmt = newAmt; //make the next year's initial the same as end of last year's final
        }
        System.out.println("Compounded " + compoundStr + " for " + years + " years.");
    }
}