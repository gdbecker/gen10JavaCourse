package gdb.section03unittests;

/**
 * @date Tuesday December 17, 2019
 * @author garrettbecker
 */

public class abba {
    // Given two Strings, a and b, return the result of putting 
    // them together in the order abba, e.g. "Hi" and "Bye" 
    // returns "HiByeByeHi". 
    //
    // abba("Hi", "Bye") -> "HiByeByeHi"
    // abba("Yo", "Alice") -> "YoAliceAliceYo"
    // abba("What", "Up") -> "WhatUpUpWhat"
    public String abba(String a, String b) {
        return "" + a + b + b + a + "";
    }
}
