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

public class stringTimesTest {
    
    private stringTimes stringTimes = new stringTimes();
    
    public stringTimesTest() {
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
     * Test of stringTimes method, of class stringTimes.
     */
    @Test
    public void testHi2() {
        String expectedResult = "HiHi";
        assertEquals(expectedResult, stringTimes.stringTimes("Hi", 2));
    }
    
    @Test
    public void testHi3() {
        String expectedResult = "HiHiHi";
        assertEquals(expectedResult, stringTimes.stringTimes("Hi", 3));
    }
    
    @Test
    public void testHi1() {
        String expectedResult = "Hi";
        assertEquals(expectedResult, stringTimes.stringTimes("Hi", 1));
    }
    
}
