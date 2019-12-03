package KnockKnock;

/**
 * @date Tuesday December 3, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class KnockKnock {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        System.out.print("Knock Knock! Guess who!! ");
        String nameGuess = inputReader.nextLine();

        //Even if you change .equals() to == it doesn't work the same!
        //If you don't use the right ca[italization, it will not work
        //Need to turn off case sensitivity in order for it to be more robust
        if (nameGuess.equals("Marty McFly")) {
            System.out.println("Hey! That's right! I'm back!");
            System.out.println(".... from the Future."); // Sorry, had to!
        } else {
            System.out.println("Dude, do I -look- like " + nameGuess);
        }
    }
}
