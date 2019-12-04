package ForTimesFor;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class ForTimesFor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Which times table shall I recite? ");
        int num = sc.nextInt();
        int numRight = 0;
        
        for (int i = 1; i<=15; i++) {
            int product = i * num;
            System.out.print(i + " * " + num + " is: ");
            int ans = sc.nextInt();
            
            if (ans == product) {
                System.out.println("Correct!");
                numRight++;
            } else if (ans != product) {
                System.out.println("Sorry no, the answer is: " + product);
            }
        }
        
        System.out.println("You got " + numRight + " correct.");
        
        double percentRight = numRight / 15.0;
        percentRight = percentRight * 100;
        System.out.println("You got " + percentRight + "% correct.");
        
        if (percentRight < 50) {
            System.out.println("You should study more!");
        } else if (percentRight > 90) {
            System.out.println("Congratulations on your score!");
        }
    }
}