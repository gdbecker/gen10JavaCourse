package gdb.userioclassexercise;

/**
 * @date Tuesday December 10, 2019
 * @author Garrett Becker
 */

import java.util.*;
public class App implements UserIO {
    Scanner sc = new Scanner(System.in);
    
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        return sc.nextDouble();
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double input = 0;
        
        do {
            System.out.println(prompt);
            input = sc.nextDouble();
        } while (input < min || input > max);
        
        return input;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return sc.nextFloat();
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float input = 0;
        
        do {
            System.out.println(prompt);
            input = sc.nextFloat();
        } while (input < min || input > max);
        
        return input;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        return sc.nextInt();
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int input = 0;
        
        do {
            System.out.println(prompt);
            input = sc.nextInt();
        } while (input < min || input > max);
        
        return input;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        return sc.nextLong();
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long input = 0;
        
        do {
            System.out.println(prompt);
            input = sc.nextLong();
        } while (input < min || input > max);
        
        return input;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }
    
}
