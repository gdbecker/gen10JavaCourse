package HiddenNuts;

/**
 * @date Thursday December 5, 2019
 * @author Garrett Becker
 */

import java.util.*;
public class HiddenNuts {
    public static void main(String[] args) {

        String[] hidingSpots = new String[100];
        Random squirrel = new Random();
        hidingSpots[squirrel.nextInt(hidingSpots.length)] = "Nut";
        System.out.println("The nut has been hidden ...");

        int count = 0;
        
        // Nut finding code should go here!
        for (String s: hidingSpots) {
            
            if (s == "Nut") {
                System.out.println("Found it! It's in spot# " + count);
                break;
            }
            count++;
        }
    }
}
