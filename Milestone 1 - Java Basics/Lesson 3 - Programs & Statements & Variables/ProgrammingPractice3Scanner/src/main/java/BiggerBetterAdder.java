package BiggerBetterAdder;

/**
 * @date Monday December 2, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class BiggerBetterAdder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, h, v;
        
        System.out.println("Enter an integer: ");
        a = sc.nextInt();
        System.out.println("Enter another integer: ");
        h = sc.nextInt();
        System.out.println("One more integer: ");
        v = sc.nextInt();
        
        System.out.println("Value of integer 1 is " + a);
        System.out.println("Value of integer 2 is " + h);
        System.out.println("Value of integer 3 is " + v);
        
        int sum = a + h + v;
        
        System.out.println("The sum of your integers is " + sum);
        System.out.println("Here's that sum again: " + sum);
    }
}