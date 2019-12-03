package TriviaNight;

/**
 * @date Tuesday December 3, 2019
 * @author Garrett Becker
 */

import java.util.Scanner;
public class TriviaNight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ans1, ans2, ans3;
        
        System.out.println("It's TRIVIA NIGHT! Are you ready?!");
        
        System.out.println("\nFIRST QUESTION!");
        System.out.println("What is the Lowest Level Programming Language?");
        System.out.println("1) Source Code		2) Assembly Language");
        System.out.println("3) C#                        4) Machine Code");
        System.out.println("\nYOUR ANSWER: ");
        ans1 = sc.nextInt();
        
        System.out.println("\nSECOND QUESTION!");
        System.out.println("Website Security CAPTCHA Forms Are Descended From the Work of?");
        System.out.println("1) Grace Hopper		2) Alan Turing");
        System.out.println("3) Charles Babbage		4) Larry Page");
        System.out.println("\nYOUR ANSWER: ");
        ans2 = sc.nextInt();
        
        System.out.println("\nLAST QUESTION!");
        System.out.println("Which of These Sci-Fi Ships Was Once Slated for a Full-Size Replica in Las Vegas?");
        System.out.println("1) Serenity			2) The Battlestar Galactica");
        System.out.println("3) The USS Enterprise	4) The Millennium Falcon");
        System.out.println("\nYOUR ANSWER: ");
        ans3 = sc.nextInt();
        
        int numCorrect = 0;
        
        if (ans1 == 4) {
            numCorrect++;
        }
        
        if (ans2 == 2) {
            numCorrect++;
        }
        
        if (ans3 == 3) {
            numCorrect++;
        }
        
        if (numCorrect == 1) {
            System.out.println("You got " + numCorrect + " right");
        } else if (numCorrect == 2) {
            System.out.println("Good work, you got " + numCorrect + " correct!");
        } else if (numCorrect == 3) {
            System.out.println("Nice job! You got " + numCorrect + " correct!");
        } else {
            System.out.println("You didn't get any right. :(");
        }
        
    }
}
