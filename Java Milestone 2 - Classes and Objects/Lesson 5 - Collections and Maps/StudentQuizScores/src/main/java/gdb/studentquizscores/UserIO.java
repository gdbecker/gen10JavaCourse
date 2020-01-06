package gdb.studentquizscores;

/**
 * @ate Wednesday December 11, 2019
 * @author Garrett Becker
 */

import java.util.*;
public class UserIO {
    Scanner sc = new Scanner(System.in);
    
    public void print(String message) {
        System.out.println(message);
    }

    public double readDouble(String prompt) {
        System.out.println(prompt);
        return sc.nextDouble();
    }

    public double readDouble(String prompt, double min, double max) {
        double input = 0;
        
        do {
            System.out.println(prompt);
            input = sc.nextDouble();
        } while (input < min || input > max);
        
        return input;
    }

    public float readFloat(String prompt) {
        System.out.println(prompt);
        return sc.nextFloat();
    }

    public float readFloat(String prompt, float min, float max) {
        float input = 0;
        
        do {
            System.out.println(prompt);
            input = sc.nextFloat();
        } while (input < min || input > max);
        
        return input;
    }

    public int readInt(String prompt) {
        System.out.println(prompt);
        return sc.nextInt();
    }

    public int readInt(String prompt, int min, int max) {
        int input = 0;
        
        do {
            System.out.println(prompt);
            input = sc.nextInt();
        } while (input < min || input > max);
        
        return input;
    }

    public long readLong(String prompt) {
        System.out.println(prompt);
        return sc.nextLong();
    }

    public long readLong(String prompt, long min, long max) {
        long input = 0;
        
        do {
            System.out.println(prompt);
            input = sc.nextLong();
        } while (input < min || input > max);
        
        return input;
    }

    public String readString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }
    
}
