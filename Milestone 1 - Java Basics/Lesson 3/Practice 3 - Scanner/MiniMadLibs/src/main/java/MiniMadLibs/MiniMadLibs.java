package MiniMadLibs;

/**
 *
 * @author Garrett Becker
 */

import java.util.Scanner;
public class MiniMadLibs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("I need a noun: ");
        String one = sc.nextLine();
        System.out.println("Now an adjective: ");
        String two = sc.nextLine();
        System.out.println("Another noun: ");
        String three = sc.nextLine();
        System.out.println("And a number: ");
        String four = sc.nextLine();
        System.out.println("Another adjective: ");
        String five = sc.nextLine();
        System.out.println("A plural noun: ");
        String six = sc.nextLine();
        System.out.println("Another one: ");
        String seven = sc.nextLine();
        System.out.println("One more: ");
        String eight = sc.nextLine();
        System.out.println("A verb (infinitive form): ");
        String nine = sc.nextLine();
        System.out.println("Same verb (past perticiple): ");
        String ten = sc.nextLine();
        
        System.out.println("*** NOW LETS GET MAD (libs) ***");
        System.out.println(one + ": the spooky frontier. These are the voyages");
        System.out.println("of the starship " + three + ". Its " + four + "-year");
        System.out.println("mission: to explore strange " + five + " " + six + ", to seek out");
        System.out.println(five + " " + seven + " and " + five + " " + eight + ",");
        System.out.println("to boldly " + nine + " where no one has " + ten + " before.");
    }
}
