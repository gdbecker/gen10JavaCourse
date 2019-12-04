package DoOrDoNot;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class DoOrDoNot {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Should I do it? (y/n) ");
        boolean doIt;

        if (input.next().equals("y")) {
            doIt = true; // DO IT!
        } else {
            doIt = false; // DONT YOU DARE!
        }

        boolean iDidIt = false;

        /*
        do {
            iDidIt = true;
            break;
        } while (doIt);
        */
        
        while(doIt) {
            iDidIt = true;
            break;
        }

        if (doIt && iDidIt) {
            System.out.println("I did it!");
        } else if (!doIt && iDidIt) {
            System.out.println("I know you said not to ... but I totally did anyways.");
        } else {
            System.out.println("Don't look at me, I didn't do anything!");
        }
        
        //If you tell it to do it, "I did it" is printed to the screen
        //If you say "n" then "I know you said not to ... but I totally did anyways." is printed
        //With the While loop instead of Do-While...
        //Inputting "y" gets the same "I did it" message as before
        //Inputting "n" writes "Don't look at me, I didn't do anything!" to the screen
    }
}
