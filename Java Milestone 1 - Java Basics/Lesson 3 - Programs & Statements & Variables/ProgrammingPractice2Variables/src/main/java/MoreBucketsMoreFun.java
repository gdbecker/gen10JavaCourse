package MoreBucketsMoreFun;

/**
 * @date Monday December 2, 2019
 * @author Garrett Becker
 */
public class MoreBucketsMoreFun {
    public static void main(String[] args) {
        // Declare ALL THE THINGS
        // (Usually it's a good idea to declare them at the beginning of the program)
        int butterflies, beetles, bugs;
        String color, size, shape, thing;
        double number;

        // Now give a couple of them some values
        butterflies = 2;
        beetles = 4;

        bugs = butterflies + beetles;
        System.out.println("There are only " + butterflies + " butterflies,");
        System.out.println("but " + bugs + " bugs total.");

        System.out.println("Uh oh, my dog ate one.");
        butterflies--;
        //Using the "-" operator to show that the dog ate a butterfly
        //Number of bugs doesn't change because "bugs" was equal to the older version of
        //butterflies and beetles, not the new
        System.out.println("Now there are only " + butterflies + " butterflies left.");
        System.out.println("But still " + bugs + " bugs left, wait a minute!!!");
        System.out.println("Maybe I just counted wrong the first time...");
    }
}
