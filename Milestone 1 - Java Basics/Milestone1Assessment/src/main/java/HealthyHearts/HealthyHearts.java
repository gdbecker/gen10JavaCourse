package HealthyHearts;

/**
 * Part of Milestone 1 Assessment
 * @author Garrett Becker
 */

import java.util.Scanner;
public class HealthyHearts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("What is your age? ");
        int age = sc.nextInt();
        
        int maxRate = 220 - age;
        double lowRange = maxRate * 0.5;
        double highRange = maxRate * 0.85;
        
        System.out.println("Your maximum heart rate should be " + maxRate + " beats per minute.");
        System.out.println("Your target HR Zone is between " + lowRange + " - " + highRange + " beats per minute.");
    }
}
