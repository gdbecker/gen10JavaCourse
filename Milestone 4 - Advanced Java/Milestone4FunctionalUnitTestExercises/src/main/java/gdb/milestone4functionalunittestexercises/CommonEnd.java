package gdb.milestone4functionalunittestexercises;

/**
 * @date Thursday December 26, 2019
 * @author garrettbecker
 */

public class CommonEnd {
    // Given 2 arrays of ints, a and b, return true if they 
    // have the same first element or they have the same 
    // last element. Both arrays will be length 1 or more. 
    //
    // commonEnd({1, 2, 3}, {7, 3}) -> true
    // commonEnd({1, 2, 3}, {7, 3, 2}) -> false
    // commonEnd({1, 2, 3}, {1, 3}) -> true
    public boolean commonEnd(int[] a, int[] b) {
        int aFirst = a[0];
        int aSize = a.length;
        int aLast = a[aSize - 1];
        
        int bFirst = b[0];
        int bSize = b.length;
        int bLast = b[bSize - 1];
        
        if (aFirst == bFirst || aLast == bLast) {
            return true;
        } else {
            return false;
        }
    }
}
