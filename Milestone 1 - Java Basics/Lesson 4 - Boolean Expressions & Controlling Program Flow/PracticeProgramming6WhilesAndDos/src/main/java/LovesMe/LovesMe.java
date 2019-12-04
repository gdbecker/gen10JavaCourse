package LovesMe;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */

public class LovesMe {
    public static void main(String[] args) {
        int petals = 34;
        System.out.println("Well here goes nothing...");
        
        while(petals > 0) {
            if (petals % 2 == 0) {
                System.out.println("It loves me NOT!");
            } else {
                System.out.println("It LOVES me!");
            }
            petals--;
        }
        
        System.out.println("I knew it! It LOVES ME!");
    }
}
