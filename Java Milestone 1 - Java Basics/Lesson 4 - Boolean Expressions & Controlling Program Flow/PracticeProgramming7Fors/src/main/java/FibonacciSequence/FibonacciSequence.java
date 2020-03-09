package FibonacciSequence;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @date Monday March 9, 2020
 * @author garrettbecker
 */

public class FibonacciSequence {
    public static void main(String[] args) {
        ArrayList<Integer> fib = new ArrayList<Integer>();
        fib.add(0);
        fib.add(1);
        
        for (int i = 2; i <= 15; i++) {
            int a = fib.get(i - 2);
            int b = fib.get(i - 1);
            fib.add(a + b);
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Which number in the sequence? 1-15");
        int choice = sc.nextInt();
        
        int num = fib.get(choice - 1);
        
        System.out.println(num);
    }
}
