package RollerCoaster;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class RollerCoaster {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("We're going to go on a roller coaster...");
        System.out.println("Let me know when you want to get off...!");

        String keepRiding = "no";
        int loopsLooped = 0;
        
        //Option 1 for condition
        /*
        while (keepRiding.equals("y")) {
            System.out.println("WHEEEEEEEEEEEEEeEeEEEEeEeeee.....!!!");
            System.out.print("Want to keep going? (y/n) :");
            keepRiding = userInput.nextLine();
            loopsLooped++;
        }
        */

        //Option 2 for condition
        while (keepRiding.equals("no")) {
            System.out.println("WHEEEEEEEEEEEEEeEeEEEEeEeeee.....!!!");
            System.out.print("Want to get off? (yes/no) :");
            keepRiding = userInput.nextLine();
            loopsLooped++;
        }
        
        System.out.println("Wow, that was FUN!");
        System.out.println("We looped that loop " + loopsLooped + " times!!");
        
        //There's no "int" in front of loopsLooped when assigning it value because
        //it was already declared before the loop started
    }
}
