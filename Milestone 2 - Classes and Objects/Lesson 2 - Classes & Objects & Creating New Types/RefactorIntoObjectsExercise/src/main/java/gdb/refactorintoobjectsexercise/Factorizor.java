package gdb.refactorintoobjectsexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Refactor into an object
import java.util.Scanner;
public class Factorizor {
    public void factorize() {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("What number would you like to factor? ");
        int num = sc.nextInt();
        System.out.println("The factors of " + num + " are:");
        
        int amtFactors = 0; //keeping track of the number of factors
        int sumFactors = 0; //totaling the sum of the factors
        
        //Cycle through all numbers from 1 to (num - 1) and figure out which
        //are factors (if the remainder is 0)
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                System.out.println(i);
                amtFactors++;
                sumFactors += i;
            }
        }
        
        System.out.println("--------------------");
        
        //Print out the total number of factors for the number
        System.out.println(num + " has " + amtFactors + " factors.");
        
        //Check to see if the input number is a "perfect" number
        if (sumFactors == num) {
            System.out.println(num + " is a perfect number.");
        } else if (sumFactors != num) {
            System.out.println(num + " is not a perfect number.");
        }
        
        //Check to see if the input number is a "prime" number
        if (amtFactors == 1) {
            System.out.println(num + " is a prime number.");
        } else if (amtFactors != 1) {
            System.out.println(num + " is not a prime number.");
        }
    }
}