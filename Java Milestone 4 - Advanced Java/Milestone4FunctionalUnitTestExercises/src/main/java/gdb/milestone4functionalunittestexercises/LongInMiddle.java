package gdb.milestone4functionalunittestexercises;

/**
 * @date Thursday December 26, 2019
 * @author garrettbecker
 */

public class LongInMiddle {
    // Given 2 Strings, a and b, return a String of the form 
    // short+long+short, with the shorter String on the outside 
    // and the longer String on the inside. The Strings will not 
    // be the same length, but they may be empty (length 0). 
    //
    // longInMiddle("Hello", "hi") -> "hiHellohi"
    // longInMiddle("hi", "Hello") -> "hiHellohi"
    // longInMiddle("aaa", "b") -> "baaab"
    public String longInMiddle(String a, String b) {
        int aSize = a.length();
        int bSize = b.length();
        String result = "";
        
        if (aSize > bSize) {
            result = b + a + b;
        } else if (bSize > aSize) {
            result = a + b + a;
        }
        
        return result;
    }
}
