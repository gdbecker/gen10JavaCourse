package gdb.milestone4functionalunittestexercises;

/**
 * @date Thursday December 26, 2019
 * @author garrettbecker
 */

public class DoubleX {
    // Given a String, return true if the first instance 
    // of "x" in the String is immediately followed by 
    // another "x". 
    //
    // doubleX("axxbb") -> true
    // doubleX("axaxxax") -> false
    // doubleX("xxxxx") -> true
    public boolean doubleX(String str) {
        boolean decision = false;
        char[] ch = str.toCharArray();
        
        for (int i = 0; i <= ch.length - 1; i++) {
            if (ch[i] == 'x') {
                if (ch[i + 1] == 'x') {
                    decision = true;
                    break;
                } else {
                    decision = false;
                    break;
                }
            }
        }
        
        return decision;
    }
}
