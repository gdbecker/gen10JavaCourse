package RockPaperScissors;

/**
 * Milestone 1 Assessment
 * @author Garrett Becker
 */

import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        
        System.out.println(" Welcome to ROCK, PAPER, SCISSORS");
        System.out.println("_/~\\_/~\\_/~\\_/~\\_/~\\_/~\\_/~\\_/~\\_");
       
        //Declare and initialize variables to use later on
        String playGame = "y";
        int compWins = 0;
        int userWins = 0;
        int ties = 0;
        int userMove = 0;
        int compMove = 0;
        boolean badInput = false;
        
        do {
            //Ask user for number of rounds to play, between 1 and 10
            System.out.print("\nHow many rounds do you want to play? (1-10): ");
            int numRounds = sc.nextInt();
            
            //Check to make sure input is in between the range
            //If not valid, leave loop and display error message
            if (numRounds > 10 || numRounds < 0) {
                System.out.println("ERROR: that's out of bounds! Exiting game :(");
                break;
            }
            
            //If input is valid, begin playing the game using a For loop
            for (int i = 1; i <= numRounds; i++) {
                
                //Check to make sure that input is valid for while playing the game
                do {
                    System.out.println("\nROUND " + i);
                    System.out.print("Make your move. Rock, Paper, or Scissors? (1,2,3): ");
                    
                    if (sc.hasNextInt()) {
                        userMove = sc.nextInt();
                        
                        if(userMove < 1 || userMove > 3) {
                            System.out.println("ERROR: Bad Input. Please try again.");
                        } else {
                            break;
                        }
                        
                    } else {
                        System.out.println("ERROR: Bad Input. Please try again.");
                        badInput = true;
                    }
                    sc.nextLine();
                } while(userMove < 1 || userMove > 3 || badInput);
                
                compMove = r.nextInt(3) + 1;
                System.out.println("My move: " + compMove);
                
                //If result is a tie:
                if (userMove == compMove) {
                    System.out.println("It's a tie!");
                    ties++;
                }
                
                //If result is Rock and Paper:
                //Be prepared for either combination
                else if ((userMove == 1 && compMove == 2) || (userMove == 2 && compMove == 1)) {
                    if (userMove == 1 && compMove == 2) {
                        System.out.println("Paper beats Rock: I won the round");
                        compWins++;
                    } else if (userMove == 2 && compMove == 1) {
                        System.out.println("Paper beats Rock: You won the round!");
                        userWins++;
                    }
                }
                
                //If result is Rock and Scissors:
                //Be prepared for either combination
                else if ((userMove == 1 && compMove == 3) || (userMove == 3 && compMove == 1)) {
                    if (userMove == 3 && compMove == 1) {
                        System.out.println("Rock beats Scissors: I won the round");
                        compWins++;
                    } else if (userMove == 1 && compMove == 3) {
                        System.out.println("Rock beats Scissors: You won the round!");
                        userWins++;
                    }
                }
                
                //If result is Paper and Scissors:
                //Be prepared for either combination
                else if ((userMove == 2 && compMove == 3) || (userMove == 3 && compMove == 2)) {
                    if (userMove == 2 && compMove == 3) {
                        System.out.println("Scissors beats Paper: I won the round");
                        compWins++;
                    } else if (userMove == 3 && compMove == 2) {
                        System.out.println("Scissors beats Paper: You won the round!");
                        userWins++;
                    }
                }
            }
            
            //Display overall game results: user wins, comp wins, ties, and overall winner
            System.out.println("\nGAME RESULTS");
            System.out.println("You won " + userWins + " times");
            System.out.println("I won " + compWins + " times");
            System.out.println("There were " + ties + " ties");
            
            if (compWins > userWins) {
                System.out.println("Overall Winner: Me");
            } else if (userWins > compWins) {
                System.out.println("Overall Winner: YOU!");
            } else if (compWins == userWins) {
                System.out.println("No overall winner - you and I tied!");
            }
            
            //Ask user if they want to play again
            //If "y" the loop will keep going, but if "n" then will exit
            System.out.print("Want to play again? (y/n): ");
            sc.nextLine();
            playGame = sc.nextLine();
            
            //Reset variables in case the user wants to play again
            userWins = 0;
            compWins = 0;
            ties = 0;
            
        } while (playGame.equals("y") || playGame.equalsIgnoreCase("yes"));
        
        //Print out final message only if the game was played at least once
        if (playGame.equals("n") || playGame.equalsIgnoreCase("no")) {
            System.out.println("Thanks for playing!");
        }
    }
}