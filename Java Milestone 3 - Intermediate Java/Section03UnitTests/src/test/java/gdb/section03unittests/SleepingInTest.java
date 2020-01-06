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
public class SleepingInTest {
    
    SleepingIn obj = new SleepingIn();
    
    public SleepingInTest() {
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
     * Tests of canSleepIn method, of class SleepingIn.
     */
    @Test
    public void testFalseFalse() {
        assertTrue(obj.canSleepIn(false, false));
    }
    
    @Test
    public void testTrueFalse() {
        assertFalse(obj.canSleepIn(true, false));
    }
    
    @Test
    public void testFalseTrue() {
        assertTrue(obj.canSleepIn(false, true));
    }
    
}
