package gdb.milestone4functionalunittestexercises;

/**
 * @date Thursday December 26, 2019
 * @author garrettbecker
 */

public class PosNeg {
    // Given two int values, return true if one is negative and 
    // one is positive. Except if the parameter "negative" is 
    // true, then return true only if both are negative. 
    //
    // posNeg(1, -1, false) -> true
    // posNeg(-1, 1, false) -> true
    // posNeg(-4, -5, true) -> true
    public boolean posNeg(int a, int b, boolean negative) {
        boolean result = false;
        if (negative) {
            if (a < 0 && b < 0) {
                result = true;
            } else {
                result  = false;
            }
        } else {
            if (a < 0 || b < 0) {
                result = true;
            } else {
                result = false;
            }            
        }
        
        return result;
    }
}
