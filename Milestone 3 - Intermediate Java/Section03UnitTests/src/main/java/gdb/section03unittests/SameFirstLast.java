package gdb.section03unittests;

/**
 * @date Wednesday December 18, 2019
 * @author garrettbecker
 */

public class SameFirstLast {
    // Given an array of ints, return true if the array is length 
    // 1 or more, and the first element and the last element are equal. 
    //
    // sameFirstLast({1, 2, 3}) -> false
    // sameFirstLast({1, 2, 3, 1}) -> true
    // sameFirstLast({1, 2, 1}) -> true
    public boolean sameFirstLast(int[] numbers) {
        if (numbers.length > 1) {
            int first = numbers[0];
            int size = numbers.length;
            int last = numbers[size - 1];
            
            if (first == last) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
