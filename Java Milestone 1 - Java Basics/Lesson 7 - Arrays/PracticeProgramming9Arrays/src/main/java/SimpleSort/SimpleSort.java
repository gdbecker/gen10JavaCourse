package SimpleSort;

/**
 * @date Thursday December 5, 2019
 * @author Garrett Becker
 */
public class SimpleSort {
    public static void main(String[] args) {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 55, 67, 88, 99};
        int[] secondHalf = {1, 4, 8, 11, 15, 18, 21, 44, 54, 79, 89, 100};

        int[] wholeNumbers = new int[24];
        int count = 0;

        // Sorting code should go here!
        for (int i = 0; i < firstHalf.length; i++) {
            if (firstHalf[i] > secondHalf[i]) {
                wholeNumbers[count] = secondHalf[i];
                count++;
                wholeNumbers[count] = firstHalf[i];
                count++;
            } else if (firstHalf[i] < secondHalf[i]) {
                wholeNumbers[count] = firstHalf[i];
                count++;
                wholeNumbers[count] = secondHalf[i];
                count++;
            }
        }
        
        System.out.println("Here ya go - all nice and ordered:");
        System.out.println("");
        for (int x : wholeNumbers) {
            System.out.print(x + " ");
        }
    }
}