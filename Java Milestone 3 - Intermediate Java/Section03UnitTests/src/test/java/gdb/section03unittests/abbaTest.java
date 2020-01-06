/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdb.section03unittests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @date Tuesday December 17, 2019
 * @author garrettbecker
 */

public class abbaTest {
    
    abba obj = new abba();
    
    public abbaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of abba method, of class abba.
     */
    @Test
    public void testHiBye() {
        String expected = "HiByeByeHi";
        assertEquals(expected, obj.abba("Hi", "Bye"));
    }
    
    @Test
    public void testWhatUp() {
        String expected = "WhatUpUpWhat";
        assertEquals(expected, obj.abba("What", "Up"));
    }
    
    @Test
    public void testYoAlice() {
        String expected = "YoAliceAliceYo";
        assertEquals(expected, obj.abba("Yo", "Alice"));
    }
    
}
