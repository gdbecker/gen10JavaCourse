package gdb.milestone4functionalunittestexercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @date Thursday December 26, 2019
 * @author garrettbecker
 */

public class CaughtSpeedingTest {
    
    CaughtSpeeding obj = new CaughtSpeeding();
    
    public CaughtSpeedingTest() {
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
     * Test of caughtSpeeding method, of class CaughtSpeeding.
     */
    @Test
    public void test60False() {
        assertEquals(0, obj.caughtSpeeding(60, false));
    }
    
    @Test
    public void test65False() {
        assertEquals(1, obj.caughtSpeeding(65, false));
    }
    
    @Test
    public void test65True() {
        assertEquals(0, obj.caughtSpeeding(65, true));
    }
    
}
