package TheCount;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class TheCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("*** I LOVE TO COUNT! LET ME SHARE MY COUNTING WITH YOU! ***");
        System.out.print("Start at : ");
        int start = sc.nextInt();
        System.out.print("Stop at : ");
        int stop = sc.nextInt();
        System.out.print("Count by : ");
        int increment = sc.nextInt();
        
        int byThree = 0;
        
        for (int i = start; i < stop; i = i + increment) {
            System.out.print(i + " ");
            
            byThree++;
            
            if (byThree == 3) {
                System.out.print("Ah ah ah!");
                System.out.println("\n");
                byThree = 0;
            }
        }
    }
}
