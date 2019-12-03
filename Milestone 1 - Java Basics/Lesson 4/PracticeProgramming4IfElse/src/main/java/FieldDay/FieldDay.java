package FieldDay;

/**
 * @date Tuesday December 3, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class FieldDay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name;
        
        System.out.println("What's your last name?");
        name = sc.nextLine();
        
        if (name.compareTo("Baggins") < 0) {
            System.out.println("Aha! You're on the team \"Red Dragons\"!");
        } else if (name.compareTo("Baggins") > 0 && name.compareTo("Dresden") < 0) {
            System.out.println("Aha! You're on the team \"Dark Wizards\"!");
        } else if (name.compareTo("Dresden") > 0 && name.compareTo("Howl") < 0) {
            System.out.println("Aha! You're on the team \"Moving Castles\"!");
        } else if (name.compareTo("Howl") > 0 && name.compareTo("Potter") < 0) {
            System.out.println("Aha! You're on the team \"Golden Snitches\"!");
        } else if (name.compareTo("Potter") > 0 && name.compareTo("Vimes") < 0) {
            System.out.println("Aha! You're on the team \"Night Guards\"!");
        } else if (name.compareTo("Vimes") > 0) {
            System.out.println("Aha! You're on the team \"Black Holes\"!");
        }
    }
}
