package DifferentKettleOfFish;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */
public class DifferentKettleOfFish {
    public static void main(String[] args) {
        
        //Using While loop
        int fish = 1;
        while(fish < 10){
            if (fish == 3){
                System.out.println("RED fish!");
            } else if (fish == 4){
                System.out.println("BLUE fish!");
            } else {
                System.out.println(fish + " fish!");
            }

            fish++;
        }
        
        //Using For loop
        for (int i = 1; i < 10; i++) {
            if (i == 3){
                System.out.println("RED fish!");
            } else if (i == 4){
                System.out.println("BLUE fish!");
            } else {
                System.out.println(i + " fish!");
            }
        }
        
        //Main change was to put the conditions within the For statement
    }
}