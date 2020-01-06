package MiniZork;

/**
 * @date Tuesday December 3, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class MiniZork {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("You are standing in an open field west of a white house,");
        System.out.println("With a boarded front door.");
        System.out.println("There is a small mailbox here.");
        System.out.print("Go to the house, or open the mailbox? ");

        String action = userInput.nextLine();

        if (action.equals("open the mailbox")) {
            System.out.println("You open the mailbox.");
            System.out.println("It's really dark in there.");
            System.out.print("Look inside or stick your hand in? ");
            action = userInput.nextLine();

            if (action.equals("look inside")) {
                System.out.println("You peer inside the mailbox.");
                System.out.println("It's really very dark. So ... so very dark.");
                System.out.print("Run away or keep looking? ");
                action = userInput.nextLine();

                if (action.equals("keep looking")) {
                    System.out.println("Turns out, hanging out around dark places isn't a good idea.");
                    System.out.println("You've been eaten by a grue.");
                } else if (action.equals("run away")) {
                    System.out.println("You run away screaming across the fields - looking very foolish.");
                    System.out.println("But you alive. Possibly a wise choice.");
                }
            } else if (action.equals("stick your hand in")) { 
                System.out.println("You find a gleaming diamond! Which triggers a cloud to form nearby.");
                System.out.println("A mysterious man appears asking if you want to teleport.");
                System.out.println("Do you teleport or stay put?");
                action = userInput.nextLine();
                
                if (action.equals("teleport")) {
                    System.out.println("You teleported to a volcano and fell in!");
                } else if (action.equals("stay put")) {
                    System.out.println("The man and diamon disappear and you live another day.");
                }
            }
        } else if (action.equals("go to the house")) { 
            System.out.println("You open the door and find a mysterious woman standing inside.");
            System.out.println("She asks you a question:");
            System.out.println("Continue inside or leave?");
            action = userInput.nextLine();
            
            if (action.equals("continue inside")) {
                System.out.println("The woman leads you to a trap door in the floor. What do you do?");
                System.out.println("Do you go inside the trap door or run away?");
                action = userInput.nextLine(); 
                
                if (action.equals("go inside")) {
                    System.out.println("You find a treasure trove of riches! Congratulations!");
                } else if (action.equals("run away")) {
                    System.out.println("The house disappears around you and you're alone. Guess it's time to go home.");
                }
            } else if (action.equals("leave")) {
                System.out.println("You leave the house and head into the backyard.");
                System.out.println("There's a large bear waiting for you!");
                System.out.println("But he speaks out to you.");
                System.out.println("Do you ignore him and run, or do you talk?");
                action = userInput.nextLine();
                
                if (action.equals("ignore him and run")) {
                    System.out.println("He chased you down and killed you. Better luck next time.");
                } else if (action.equals("talk")) {
                    System.out.println("Good choice! He gives you a treasure chest full of riches.");
                }
            }
        }
    }
}
