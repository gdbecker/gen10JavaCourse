package SpringForwardFallBack;

/**
 * @date Wednesday December 4, 2019
 * @author Garrett Becker
 */
public class SpringForwardFallBack {
    public static void main(String[] args) {
        System.out.println("It's Spring...!");
        
        //Start point: 0; stop point: 9 (original problem)
        for (int i = 1; i < 11; i++) {
            System.out.print(i + ", ");
        }

        //Start point: 10; stop point: 1
        System.out.println("\nOh no, it's fall...");
        for (int i = 10; i > 0; i--) {
            System.out.print(i + ", ");
        }
    }
}
