package WaitAWhile;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */
public class WaitAWhile {
    public static void main(String[] args) {
        int timeNow = 5;
        int bedTime = 10;

        while (timeNow < bedTime) {
            System.out.println("It's only " + timeNow + " o'clock!");
            System.out.println("I think I'll stay up just a liiiiittle longer....");
            timeNow++; // Time passes
        }

        System.out.println("Oh. It's " + timeNow + " o'clock.");
        System.out.println("Guess I should go to bed ...");
        
        //Change bedTime's value to 11 will let the loop go through one more time
        //Change timeNow to 11 will skip the loop (since it's greater than bedTime) and
        //only print out the last two println statements
        //If you take out "timeNow++" you'll get stuck in an infite loop
    }
}
