package BarelyControlledChaos;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */

import java.util.Random;
public class BarelyControlledChaos {
    public static void main(String[] args) {

        String color = getColor(); // call color method here 
        String animal = getAnimal(); // call animal method again here 
        String colorAgain = getColor(); // call color method again here 
        int weight = getNumber(5, 200); // call number method, 
            // with a range between 5 - 200 
        int distance = getNumber(10, 20); // call number method, 
            // with a range between 10 - 20 
        int number = getNumber(10000, 20000); // call number method, 
            // with a range between 10000 - 20000 
        int time = getNumber(2, 6); // call number method, 
            // with a range between 2 - 6            
    
        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", "
            + weight + "lb " + " miniature " + animal 
            + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over " 
            + number + " " + colorAgain + " poppies for nearly " 
            + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, " 
            + "let me tell you!");
    } 
    
    public static String getColor() {
        Random r = new Random();
        int num = r.nextInt(5);
        String color = "";
        
        switch (num) {
            case 0:
                color = "navy";
                break;
            case 1:
                color = "crimson";
                break;
            case 2:
                color = "green";
                break;
            case 3:
                color = "white";
                break;
            case 4:
                color = "purple";
                break;
        }
        
        return color;
    }
    
    public static String getAnimal() {
        Random r = new Random();
        int num = r.nextInt(5);
        String animal = "";
        
        switch (num) {
            case 0:
                animal = "giraffe";
                break;
            case 1:
                animal = "penguin";
                break;
            case 2:
                animal = "dog";
                break;
            case 3:
                animal = "tiger";
                break;
            case 4:
                animal = "rhino";
                break;
        }
        
        return animal;
    }
    
    public static int getNumber(int min, int max) {
        Random r = new Random();
        
        return r.nextInt(max - min) + min;
    }
}