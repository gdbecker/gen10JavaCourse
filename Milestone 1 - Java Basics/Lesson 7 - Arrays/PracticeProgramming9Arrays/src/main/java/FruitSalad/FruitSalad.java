package FruitSalad;

/**
 * @date Thursday December 5, 2019
 * @author Garrett Becker
 */
public class FruitSalad {
    public static void main(String[] args) {
        String[] fruit = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry", "Beefsteak Tomato", "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple",  "Raspberry", "Blood Orange", "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana", "Pineapple", "Florida Orange", "Kiku Apple", "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};
        
        String[] fruitSalad = new String[fruit.length];
        int fruitTypes = 0;
        int numOranges = 0;
        int numApples = 0;
        int count = 0;
        
        // Code Recipe for fruit salad should go here!
        for (int i = 0; i < fruit.length; i++) {
            String s = fruit[i];
            
            //Check for apples; ensure only 3 are selected
            if (s.contains("Apple")) {
                if (numApples < 3) {
                    fruitSalad[count] = s;
                    numApples++;
                    count++;
                }
                
                //Add apples to fruitTypes, but only once
                if (numApples == 1) {
                    fruitTypes++;
                }
            }
            
            //Check for apples; ensure only 2 are selected
            else if (s.contains("Orange")) {
                if (numOranges < 2) {
                    fruitSalad[count] = s;
                    numOranges++;
                    count++;
                }
                
                //Add oranges to fruitTypes, but only once
                if (numOranges == 1) {
                    fruitTypes++;
                }
            } 
            
            //Add as many berries as needed
            else if (s.contains("berry")) {
                fruitSalad[count] = s;
                count++;
                fruitTypes++;
            }
            
            //Ignore the tomatoes
            else if (s.contains("Tomato")) {
                
            }
            
            //Add other types of fruit
            else {
                fruitSalad[count] = s;
                count++;
                fruitTypes++;
            }
            
            //Exit For loop if you get to 12 different types of fruit
            if (fruitTypes == 12) {
                break;
            }
        }
        
        //Count how many fruit are in the fruit salad
        int numFruit = 0;
        for (int x = 0; x < fruitSalad.length; x++) {
            if (fruitSalad[x] != null) {
                numFruit++;
            }
        }
        
        System.out.println("Here's our fruit salad!");
        System.out.println("We have " + fruitTypes + " types of fruit.");
        System.out.println("And " + numFruit + " total fruit in the salad!");
        for (int j = 0; j < fruitSalad.length; j++) {
            if (fruitSalad[j] != null) {
                System.out.println(fruitSalad[j]);
            }
        }
    }
}