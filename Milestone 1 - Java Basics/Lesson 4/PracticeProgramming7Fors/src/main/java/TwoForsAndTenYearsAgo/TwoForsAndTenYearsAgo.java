package TwoForsAndTenYearsAgo;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class TwoForsAndTenYearsAgo {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.print("What year would you like to count back from? ");
        int year = userInput.nextInt();

        for (int i = 0; i <= 10; i++) {
            System.out.println(i + " years ago would be " + (year - i));
        }

        System.out.println("\nI can count backwards using a different way too...");

        for (int i = year; i >= year - 20; i--) {
            System.out.println( (year - i) + " years ago would be " + i);
        }
        
        //Range for both loops is from year to (year-10)
        //First one is clearer to me, mainly because the syntax is easy for me to understand
    }
}