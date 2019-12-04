package YourLifeInMovies;

/**
 * @date Tuesday December 3, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class YourLifeInMovies {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long year;
        String name;
        
        System.out.println("Hey, let's play a game! What's your name?");
        name = sc.nextLine();
        System.out.println("Ok, " + name + ", what year were you born?");
        year = sc.nextLong();
        
        System.out.println("Well " + name + "...");
        
        if (year < 2005) {
            System.out.println("Did you know that Pixar's Up came out half a decade ago?");
        }
        
        if (year < 1995) {
            System.out.println("The first Harry Potter movie came out over 15 years ago!");
        } 
        
        if (year < 1985) {
            System.out.println("Space Jam came out not last decade, but the one before THAT.");
        }
        
        if (year < 1975) {
            System.out.println("Did you know that the original Jurassic Park release date is closer to the date of the first lunar landing than it is today!");
        }
        
        if (year < 1965) {
            System.out.println("The MASH TV series has been around for almost half a century!");
        }
        
        
    }
}
