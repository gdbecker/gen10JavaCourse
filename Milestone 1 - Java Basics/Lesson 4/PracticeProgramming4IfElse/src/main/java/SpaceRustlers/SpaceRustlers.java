package SpaceRustlers;

/**
 * @date Tuesday December 3, 2019
 * @author Garrett Becker
 */
public class SpaceRustlers {
    public static void main(String[] args) {
        int spaceships = 10;
        int aliens = 25;
        int cows = 100;

        if(aliens > spaceships){
            System.out.println("Vrroom, vroom! Let's get going!");
        } else{
            System.out.println("There aren't enough green guys to drive these ships!");
        }

        //The "else if" gives other options of conditions within the same tree - only one is evaluated
        //A series of multiple "if" conditions makes it possible for multiple to be executed
        //Removing the "else" from an "else if" makes each "if" statement its own decision tree
        
        if(cows == spaceships){
            System.out.println("Wow, way to plan ahead! JUST enough room for all these walking hamburgers!");
        } else if (cows > spaceships){
            System.out.println("Dangit! I don't how we're going to fit all these cows in here!");
        } else {
            System.out.println("Too many ships! Not enough cows.");
        }
        
        if (cows > aliens || cows == aliens) {
            System.out.println("Oh no! The herds got restless and took over! Looks like _we're_ hamburger now!!");
        } else if (cows < aliens) {
            System.out.println("Hurrah, we've got the grub! Hamburger party on Alpha Centauri!");
        }
    }
}
