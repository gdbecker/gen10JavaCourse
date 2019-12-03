package DoItBetter;

/**
 *
 * @author Garrett Becker
 */

import java.util.Scanner;
public class DoItBetter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int miles, hotdogs, languages;
        int milesBrag, hotdogsBrag, languagesBrag;
        
        System.out.println("How many miles can you run?");
        miles = sc.nextInt();
        milesBrag = (miles * 2) + 1;
        System.out.println("Well I can run " + milesBrag + " miles!");
        
        System.out.println("\nHow many hotdogs can you eat at once?");
        hotdogs = sc.nextInt();
        hotdogsBrag = (hotdogs * 2) + 1;
        System.out.println("Hah! I can eat " + hotdogsBrag + " hotdogs!");
        
        System.out.println("\nHow many languages do you know?");
        languages = sc.nextInt();
        languagesBrag = (languages * 2) + 1;
        System.out.println("I am a linguistics master & know " + languagesBrag + " languages!");
    }
}
