package gdb.refactorintoobjectsexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Refactor into an object
import java.util.Scanner;
import java.util.Random;
public class LuckySevens {
    public void playGame() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("------- Welcome to LUCKY SEVENS -------");
        System.out.print("How many dollars do you have? ");
        int money = sc.nextInt();
        
        //Initialize veriables
        int die1 = 0;
        int die2 = 0;
        int numRolls = 0;
        int maxAmt = 0; //max amount of money won during game
        int maxAtIndex = 0; //# of turn when max amount was won
        int count = 0; //keep track of number of loop iterations
        
        //Play the game, stop when money held runs out
        while (money > 0) {
            die1 = rollDice();
            die2 = rollDice();
            
            //Check if new money amount is greater than existing max amount won
            //If it is, change it!
            if (money > maxAmt) {
                maxAmt = money;
                maxAtIndex = count;
            }  
            
            if ((die1 + die2) == 7 ) {
                money += 4;
                numRolls++;
            } else {
                money -= 1;
                numRolls++;
            }
            count++;
        }
        
        //Display game results
        System.out.println("\nGAME RESULTS");
        System.out.println("You are broke after " + numRolls + " rolls.");
        System.out.println("You should have quit after " + maxAtIndex + " rolls when you had $" + maxAmt + ".");
    }
    
    public static int rollDice() {
        Random r = new Random();
        return r.nextInt(6) + 1;
    }
}