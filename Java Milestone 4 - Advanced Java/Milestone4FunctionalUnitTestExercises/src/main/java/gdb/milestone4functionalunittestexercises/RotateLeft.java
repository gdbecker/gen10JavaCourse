package gdb.milestone4functionalunittestexercises;

/**
 * @date Thursday December 26, 2019
 * @author garrettbecker
 */

public class RotateLeft {
    // Given an array of ints, return an array with the elements 
    // “rotated left" so {1, 2, 3} yields {2, 3, 1}. 
    //
    // rotateLeft({1, 2, 3}) -> {2, 3, 1}
    // rotateLeft({5, 11, 9}) -> {11, 9, 5}
    // rotateLeft({7, 0, 0}) -> {0, 0, 7}
    public int[] rotateLeft(int[] numbers) {
        int size = numbers.length;
        int[] result = new int[size];
        
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                result[i] = numbers[0];
            } else {
                result[i] = numbers[i + 1];
            }
        }
        
        return result;
    }
}
