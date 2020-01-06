package gdb.milestone4functionalunittestexercises;

/**
 * @date Thursday December 26, 2019
 * @author garrettbecker
 */

public class FirstHalf {
    // Given a String of even length, return the first half. 
    // So the String "WooHoo" yields "Woo". 
    //
    // firstHalf("WooHoo") -> "Woo"
    // firstHalf("HelloThere") -> "Hello"
    // firstHalf("abcdef") -> "abc"
    public String firstHalf(String str) {
        int length = str.length();
        int halfLength = length / 2;
        char[] ch = str.toCharArray();
        String result = "";
        
        for (int i = 0; i < halfLength; i++) {
            String cToS = Character.toString(ch[i]);
            result += cToS;
        }
        
        return result;
    }
}
