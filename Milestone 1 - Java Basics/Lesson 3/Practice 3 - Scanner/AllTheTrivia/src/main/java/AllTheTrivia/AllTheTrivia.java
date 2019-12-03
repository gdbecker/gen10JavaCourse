package AllTheTrivia;

/**
 *
 * @author Garrett Becker
 */

import java.util.Scanner;
public class AllTheTrivia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String ans1, ans2, ans3, ans4;
        
        System.out.println("1,024 Gigabytes is equal to one what? ");
        ans1 = sc.nextLine();
        System.out.println("In our Solar System, which is the only planet that rotates clockwise? ");
        ans2 = sc.nextLine();
        System.out.println("The largest volcano ever discovered in our Solar System is located on which planet? ");
        ans3 = sc.nextLine();
        System.out.println("What is the most abundant element in the earth's atmosphere? ");
        ans4 = sc.nextLine();
        
        System.out.println("\nWow, 1,024 Gigabytes is a " + ans2 + "!");
        System.out.println("I didn't know that the largest ever volcano was discovered on " + ans4 + "!");
        System.out.println("That's amazing that " + ans3 + " is the most abundant element in the atmosphere...");
        System.out.println(ans1 + " is the only planet that rotates clockwise, neat!");
    }
}
