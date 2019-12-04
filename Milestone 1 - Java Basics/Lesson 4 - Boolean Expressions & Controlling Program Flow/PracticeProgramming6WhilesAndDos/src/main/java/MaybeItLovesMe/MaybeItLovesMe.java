package MaybeItLovesMe;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */

import java.util.Random;
public class MaybeItLovesMe {
    public static void main(String[] args) {
        Random r = new Random();
        int petals = r.nextInt((89 - 13) + 1) + 13;
        int num = petals;
        System.out.println("Well here goes nothing...");
        
        while(petals > 0) {
            if (petals % 2 == 0) {
                System.out.println("It LOVES me!");
                
                if (petals == 1) {
                    System.out.println("Oh, wow! It really LOVES me!");
                }
                
            } else {
                System.out.println("It loves me NOT!");
                
                if (petals == 1) {
                    System.out.println("Awwww, bummer.");
                }
                
            }
            petals--;
        }
    }
}
