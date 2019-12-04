package BewareTheKraken;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
import java.util.Random;
public class BewareTheKraken {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        
        System.out.println("Already, get those flippers and wetsuit on - we're going diving!");
        System.out.println("Here we goooOOooOooo.....! *SPLASH*");

        int depthDivedInFt = 0;

        // Turns out the ocean is only so deep, 36200 at the deepest survey,
        // so if we reach the bottom ... we should probably stop.
        //Changing the condition to be just "true" will make this an infiite loop
        while(depthDivedInFt < 36200){
            System.out.println("So far, we've swum " + depthDivedInFt + " feet");
            
            //Add in code for asking to exit diving
            System.out.print("Do you want to stop?! (y/n) ");
            String ans = sc.nextLine();
            
            //Create option for exiting the loop if user wants to 
            if (ans.equals("y")) {
                break;
            }

            //Print out a random fish type to the screen
            int fish = r.nextInt(3);
            if (fish == 0) {
                System.out.println("Just passed a school of clownfish!");
            } else if (fish == 1) {
                System.out.println("Lookout for the stingray over there!");
            } else if (fish == 2) {
                System.out.println("Swim around that blue tang!");
            }
            
            if(depthDivedInFt >= 20000){
                System.out.println("Uhhh, I think I see a Kraken, guys ....");
                System.out.println("TIME TO GO!");
                break;
            }

            // I can swim, really fast! 500ft at a time!
            depthDivedInFt += 1000;
        }
        System.out.println("");
        System.out.println("We ended up swimming " + depthDivedInFt + " feet down.");
        System.out.println("I bet we can do better next time!");
    }
}
