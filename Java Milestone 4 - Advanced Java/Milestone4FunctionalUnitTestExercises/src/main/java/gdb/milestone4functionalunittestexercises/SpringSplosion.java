package gdb.milestone4functionalunittestexercises;

/**
 * @date Thursday December 26, 2019
 * @author garrettbecker
 */

public class SpringSplosion {
    // Given a non-empty String like "Code" return a String like 
    // “CCoCodCode".  (first char, first two, first 3, etc)
    //
    // stringSplosion("Code") -> "CCoCodCode"
    // stringSplosion("abc") -> "aababc"
    // stringSplosion("ab") -> "aab"
    public String stringSplosion(String str) {
        char[] ch = str.toCharArray();
        int size = ch.length;
        String sub = "";
        String result = "";
        
        for (int i = 1; i <= size; i++) {
            for (int j = 0; j < i; j++) {
                sub = Character.toString(ch[j]);
                result += sub;
            }
        }
        
        return result;
    } 
}
