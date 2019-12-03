package CoinFlipper;

/**
 * @date Tuesday December 3, 2019
 * @author Garrett Becker
 */

import java.util.*;
import static java.lang.Math.*;
public class CoinFlipper {
    public static void main(String[] args) {
        double x = Math.round(Math.random());
        int w = (int)x;
        String side; 
        
        if (w == 1) {
            side = "TAILS";
        } else {
            side = "HEADS";
        }
        
        System.out.println("Ready, Set, Flip....!!");
        System.out.println("You got " + side + "!");
    }
}
