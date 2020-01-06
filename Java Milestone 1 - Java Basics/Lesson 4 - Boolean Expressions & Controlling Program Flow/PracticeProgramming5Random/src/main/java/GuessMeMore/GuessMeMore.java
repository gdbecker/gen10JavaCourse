package GuessMeMore;

/**
 * @date Tuesday December 3, 2019
 * @author Garrett Becker
 */

import java.util.Random;
import java.util.Scanner;
public class GuessMeMore {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int x = r.nextInt(200) - 100;
        int guess;
        
        System.out.println("I've chosen a number between -100 and 100. Betcha can't guess it!");
        
        System.out.println("\nYour guess: ");
        guess = sc.nextInt();
            
        if (guess < x) {
            System.out.println("Ha, nice try - too low! Try again!");
        } else if (guess > x) {
            System.out.println("Nice try, way too high!");
        } else if (guess == x) {
            System.out.println("Wow, nice guess! That was it!");
        }
        
        System.out.println("\nYour guess: ");
        guess = sc.nextInt();
        
        if (guess < x) {
            System.out.println("Ha, nice try - too low! Try again!");
        } else if (guess > x) {
            System.out.println("Nice try, way too high!");
        } else if (guess == x) {
            System.out.println("Wow, nice guess! That was it!");
        }
    }
}
