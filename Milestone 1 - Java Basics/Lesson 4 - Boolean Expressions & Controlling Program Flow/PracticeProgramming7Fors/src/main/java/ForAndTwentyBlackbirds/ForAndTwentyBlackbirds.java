package ForAndTwentyBlackbirds;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */
public class ForAndTwentyBlackbirds {
    public static void main(String[] args) {
        int birdsInPie = 0;
        
        //To make range from 1-24, I changed i to start at 1 and then stop before i=25
        for (int i = 1; i < 25; i++) {
            System.out.println("Blackbird #" + i + " goes into the pie!");
            birdsInPie++;
        }

        System.out.println("There are " + birdsInPie + " birds in there!");
        System.out.println("Quite the pie full!");
    }
}
