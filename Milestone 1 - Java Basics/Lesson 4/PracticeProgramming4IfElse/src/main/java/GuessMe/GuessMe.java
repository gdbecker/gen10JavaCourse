package GuessMe;

/**
 * @date Tuesday December 3, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class GuessMe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = 11;
        int guess;
        
        System.out.println("I've chosen a number. Betcha can't guess it!");
        System.out.println("Your guess: ");
        guess = sc.nextInt();
        
        if (guess == num) {
            System.out.println("\nWow, nice guess! That was it!");
        } else if (guess < num) {
            System.out.println("\nHa, nice try - too low! I chose 11.");
        } else {
            System.out.println("\nToo bad, way too high. I chose 11.");
        }
    }
}
