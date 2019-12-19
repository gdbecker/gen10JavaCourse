package gdb.section03unittests;

/**
 * @date Wednesday December 18, 2019
 * @author garrettbecker
 */

public class FirstLast6 {
    // Given an array of ints, return true if 6 appears as either the 
    // first or last element in the array. The array will be length 1 or more. 
    //
    // firstLast6({1, 2, 6}) -> true
    // firstLast6({6, 1, 2, 3}) -> true
    // firstLast6({13, 6, 1, 2, 3}) -> false
    public boolean firstLast6(int[] numbers) {
        int first = numbers[0];
        int size = numbers.length;
        int last = numbers[size - 1];
        
        if (first == 6 || last == 6) {
            return true;
        } else {
            return false;
        }
    }
}
