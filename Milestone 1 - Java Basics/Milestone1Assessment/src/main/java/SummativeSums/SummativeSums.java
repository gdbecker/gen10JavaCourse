package SummativeSums;

/**
 * Milestone 1 Assessment
 * @author Garrett Becker
 */
public class SummativeSums {
    public static void main(String[] args) {
        //Begin by defining the int arrays to contain the specifified values from
        //the problem description.
        int[] array1 = { 1, 90, -33, -55, 67, -16, 28, -55, 15 };
        int[] array2 = { 999, -60, -77, 14, 160, 301 };
        int[] array3 = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99 };
        
        //Run each int array through the "addArray" method created below the main method
        int sum1 = addArray(array1);
        int sum2 = addArray(array2);
        int sum3 = addArray(array3);
        
        //Print out the results of each array's sum to the console.
        System.out.println("#1 Array Sum: " + sum1);
        System.out.println("#2 Array Sum: " + sum2);
        System.out.println("#3 Array Sum: " + sum3);
          
    }
    
    //Custom method created to take a single-dimension int array and add each
    //of its elements. Returns an int that is the sum of the array.
    public static int addArray(int array[]) {
        int sum = 0;
        
        //Loop through each array and change the "sum" variable to continuously
        //add each array element.
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        
        return sum;
    } 
}