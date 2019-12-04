package LazyTeenager;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */

import java.util.Random;
public class LazyTeenager {
    public static void main(String[] args) {
        Random r = new Random();
        int count = 1;
        int upperBound = 0; //percent chance that teenager will clean room
        boolean cleanRoom = false;
        
        do {
            System.out.println("Clean your room!! (x" + count + ")");
            int chance = r.nextInt(101) + 1;
            
            if (chance <= upperBound) {
                System.out.print("FINE! I'LL CLEAN MY ROOM. BUT I REFUSE TO EAT MY PEAS.");
                break;
            }
            
            //Exit loop if told 16 times to clean room
            if (count == 16) {
                System.out.print("That's IT, I'm doing it!!! YOU'RE GROUNDED AND I'M TAKING YOUR XBOX!");
                break;
            }
            count++;
            upperBound = upperBound + 5;
        } while (cleanRoom == false);
        
    }
}
