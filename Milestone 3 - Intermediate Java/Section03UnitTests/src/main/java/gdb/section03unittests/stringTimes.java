package gdb.section03unittests;

/**
 * @date Tuesday December 17, 2019
 * @author garrettbecker
 */

public class stringTimes {
    // Given a String and a non-negative int n, return a larger String 
    // that is n copies of the original String. 
    //
    // stringTimes("Hi", 2) -> "HiHi"
    // stringTimes("Hi", 3) -> "HiHiHi"
    // stringTimes("Hi", 1) -> "Hi"
    public String stringTimes(String str, int n) {
        String result = "";
        for(int i = 1; i <= n; i++) {
            result += str;
        }
        return result;
    }
}
