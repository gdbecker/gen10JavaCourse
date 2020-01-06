package gdb.milestone4functionalunittestexercises;

/**
 * @date Thursday December 26, 2019
 * @author garrettbecker
 */

public class EveryOther {
    // Given a String, return a new String made of every other 
    // char starting with the first, so "Hello" yields "Hlo". 
    //
    // everyOther("Hello") -> "Hlo"
    // everyOther("Hi") -> "H"
    // everyOther("Heeololeo") -> "Hello"
    public String everyOther(String str) {
        char[] ch = str.toCharArray();
        String result = "";
        for (int i = 0; i < ch.length; i++) {
            if (i % 2 == 0) {
                result += ch[i];
            } 
        }
        
        return result;
    }
}
