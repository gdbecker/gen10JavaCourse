package HealthyHearts;

/**
 * Milestone 1 Assessment
 * @author Garrett Becker
 */

import java.util.Scanner;

public class HealthyHearts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //Begin by asking the user for their age which is used throughout the program
        System.out.print("What is your age? ");
        int age = sc.nextInt();
        
        //Make key calculations including maximum heart rate and target heart rate zone
        //Round the lowRange and highRange values to get even integers
        int maxRate = 220 - age;
        int lowRange = (int)Math.round(maxRate * 0.5);
        int highRange = (int)Math.round(maxRate * 0.85);
        
        //Display results to the console
        System.out.println("Your maximum heart rate should be " + maxRate + " beats per minute.");
        System.out.println("Your target HR Zone is between " + lowRange + " - " + highRange + " beats per minute.");
    }
}