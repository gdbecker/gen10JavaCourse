package StayPositive;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class StayPositive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("What number should I count down from? ");
        int num = sc.nextInt();
        
        System.out.println("\nHere goes! ");
        
        while (num >= 0) {
            System.out.print("\n" + num + " ");
            num--;
            int countDown = 9;
            
            while (num >= 0 && countDown > 0) {
                System.out.print(num + " ");
                num--;
                countDown--;
            }
        }
    }
}
