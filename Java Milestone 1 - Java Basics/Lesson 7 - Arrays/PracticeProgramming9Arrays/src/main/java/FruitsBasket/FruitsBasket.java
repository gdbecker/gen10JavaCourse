package FruitsBasket;

/**
 * @date Thursday December 5, 2019
 * @author Garrett Becker
 */
public class FruitsBasket {
    public static void main(String[] args) {
        String[] fruit = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange"};

        // Fruit sorting code goes here!
        int numFruit = fruit.length; //Get total number of fruit in the basket
        int numApples = 0;
        int numOranges = 0;
        
        for (int i = 0; i < fruit.length; i++) {
            if (fruit[i] == "Apple") {
                numApples++; //Find number of apples, use to initialize apples array
            } else if (fruit[i] == "Orange") {
                numOranges++; //Find number of oranges, use to initialize oranges array
            }
        }
        
        String[] apples = new String[numApples];
        String[] oranges = new String[numOranges];
        
        //Sort fruits into their baskets
        for (int j = 0; j < numApples; j++) {
            apples[j] = "Apple";
        }
        
        for (int x = 0; x < numOranges; x++) {
            oranges[x] = "Orange";
        }
        
        System.out.println("Total# of Fruit in Basket: " + numFruit);
        System.out.println("Number of Apples: " + numApples);
        System.out.println("Number of Oranges: " + numOranges);
    }
}
