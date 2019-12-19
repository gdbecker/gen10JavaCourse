package gdb.section03unittests;

/**
 * @date Wednesday December 18, 2019
 * @author garrettbecker
 */

public class FrontTimes {
    // Given a String and a non-negative int n, we'll say that the 
    // front of the String is the first 3 chars, or whatever is there 
    // if the String is less than length 3. Return n copies of the front; 
    //
    // frontTimes("Chocolate", 2) -> "ChoCho"
    // frontTimes("Chocolate", 3) -> "ChoChoCho"
    // frontTimes("Abc", 3) -> "AbcAbcAbc"
    public String frontTimes(String str, int n) {
        String partial = str.substring(0, 3);
        String result = "";
        
        for(int i = 1; i <= n; i++) {
            result += partial;
        }
        return result;
    }
}
