package com.sg.hellosecurity.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @date Friday January 31, 2020
 * @author garrettbecker
 */

public class PWEnc {
    public static void main(String[] args) {
        String clearTxtPw = "password";
        // BCrypt
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPw = encoder.encode(clearTxtPw);
        System.out.println(hashedPw);
    }
}
