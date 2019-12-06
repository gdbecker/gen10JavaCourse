package DogGenetics;

/**
 * Milestone 1 Assessment
 * @author Garrett Becker
 */

import java.util.Scanner;
import java.util.Random;

public class DogGenetics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        
        //Begin by asking for the dog's name and print out beginning of report
        System.out.print("What is your dog's name? ");
        String name = sc.nextLine();
        System.out.println("Well then, I have this highly reliable report on " + name + "'s prestigious background right here.");
        System.out.println("\n" + name + " is:");
        
        //Assign variables to names of 5 different dog breeds
        String dogBreed1 = "Pug";
        String dogBreed2 = "Golden Retriever";
        String dogBreed3 = "Bulldog";
        String dogBreed4 = "Husky";
        String dogBreed5 = "Samoyed";
        
        //Initialize variables for percentages of each breed
        int percent1 = 0;
        int percent2 = 0;
        int percent3 = 0;
        int percent4 = 0;
        int percent5 = 0;
        
        //Assign random values to the first four percentage variables
        //Only keep running the loop if the first four already add to 100
        do {
            percent1 = r.nextInt(25) + 1;
            percent2 = r.nextInt(25) + 1;
            percent3 = r.nextInt(25) + 1;
            percent4 = r.nextInt(25) + 1;
        } while (percent1 + percent2 + percent3 + percent4 == 100);
        
        //Use the difference between 100 and the sum of the first four percentage
        //variables to calculate the fifth percent
        percent5 = 100 - (percent1 + percent2 + percent3 + percent4);
        
        //Print results to the console
        System.out.println(percent1 + "% " + dogBreed1);
        System.out.println(percent2 + "% " + dogBreed2);
        System.out.println(percent3 + "% " + dogBreed3);
        System.out.println(percent4 + "% " + dogBreed4);
        System.out.println(percent5 + "% " + dogBreed5);
        System.out.println("\nWow, that's QUITE the dog!");
    }
}