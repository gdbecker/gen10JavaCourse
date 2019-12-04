package ForTimes;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class ForTimes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Which times table shall I recite? ");
        int num = sc.nextInt();
        
        for (int i = 1; i<=15; i++) {
            int product = i * num;
            System.out.println(i + " * " + num + " is: " + product);
        }
    }
}
