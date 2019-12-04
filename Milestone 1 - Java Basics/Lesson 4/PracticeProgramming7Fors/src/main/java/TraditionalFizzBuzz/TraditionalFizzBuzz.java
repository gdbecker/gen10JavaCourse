package TraditionalFizzBuzz;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class TraditionalFizzBuzz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("How many fizzing and buzzing units do you need in your life? ");
        int num = sc.nextInt();
        
        for (int i = 0; i<=num; i++) {
            if (i % 3 == 0 && i % 5 == 0 && i != 0) {
                System.out.println("fizz buzz");
            } else if (i % 3 == 0 && i != 0) {
                System.out.println("fizz");
            } else if (i % 5 == 0 && i!= 0) {
                System.out.println("buzz");
            } else {
                System.out.println(i);
            }
        }
        
        System.out.println("TRADITION!!!!!");
    }
}