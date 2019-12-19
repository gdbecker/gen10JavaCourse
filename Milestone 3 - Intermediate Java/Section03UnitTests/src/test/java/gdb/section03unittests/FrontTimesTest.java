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
 * @date Wednesday December 18, 2019
 * @author garrettbecker
 */

public class FrontTimesTest {
    
    FrontTimes obj = new FrontTimes();
    
    public FrontTimesTest() {
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
     * Tests of frontTimes method, of class FrontTimes.
     */
    @Test
    public void testChocolate2() {
        String expected = "ChoCho";
        assertEquals(expected, obj.frontTimes("Chocolate", 2));
    }
    
    @Test
    public void testChocolate3() {
        String expected = "ChoChoCho";
        assertEquals(expected, obj.frontTimes("Chocolate", 3));
    }
    
    @Test
    public void testAbc3() {
        String expected = "AbcAbcAbc";
        assertEquals(expected, obj.frontTimes("Abc", 3));
    }
    
}
