package gdb.milestone4functionalunittestexercises;

/**
 * @date Thursday December 26, 2019
 * @author garrettbecker
 */

public class MultipleEndings {
    // Given a String, return a new String made of 3 copies 
    // of the last 2 chars of the original String. The String 
    // length will be at least 2. 
    //
    // multipleEndings("Hello") -> "lololo"
    // multipleEndings("ab") -> "ababab"
    // multipleEndings("Hi") -> "HiHiHi"
    public String multipleEndings(String str) {
        char[] ch = str.toCharArray();
        
        int size = ch.length;
        char last = ch[size - 1];
        String lastString = Character.toString(last);
        char nextToLast = ch[size - 2];
        String nextToLastString = Character.toString(nextToLast);
        
        return nextToLastString + lastString + nextToLastString + lastString + nextToLastString + lastString;
    }
}
