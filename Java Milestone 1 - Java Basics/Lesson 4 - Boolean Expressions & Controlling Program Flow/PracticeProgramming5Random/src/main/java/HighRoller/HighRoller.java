package HighRoller;

/**
 * @date Tuesday December 3, 2019
 * @author Garrett Becker
 */

import java.util.Random;
import java.util.Scanner;
public class HighRoller {
    public static void main(String[] args) {
        Random diceRoller = new Random();
        Scanner sc = new Scanner(System.in);

        int numSides;

        System.out.println("How many sides on your dice?");
        numSides = sc.nextInt();
        
        int rollResult = diceRoller.nextInt(numSides) + 1;
        
        System.out.println("TIME TO ROOOOOOLL THE DICE!");
        System.out.println("I rolled a " + rollResult);

        if (rollResult == 1) {
            System.out.println("You rolled a critical failure!");
        } else if (rollResult == numSides) {
            System.out.println("You rolled a critical! Nice Job!");
        }
    }
}
