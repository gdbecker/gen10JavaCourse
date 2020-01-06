package gdb.milestone4functionalunittestexercises;

/**
 * @date Thursday December 26, 2019
 * @author garrettbecker
 */

public class NearHundred {
    // Given an int n, return true if it is within 10 of 100 
    // or 200.
    // Hint: Check out the Math class for absolute value
    //
    // nearHundred(103) -> true
    // nearHundred(90) -> true
    // nearHundred(89) -> false
    public boolean nearHundred(int n) {
        int num = Math.abs(n);
        if ((num >= 90 && num <= 110) || (num >=190 && num <= 210)) {
            return true;
        } else {
            return false;
        }
    }
}
