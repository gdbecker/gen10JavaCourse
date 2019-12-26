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

public class PosNegTest {
    
    PosNeg obj = new PosNeg();
    
    public PosNegTest() {
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
     * Test of posNeg method, of class PosNeg.
     */
    @Test
    public void test1Neg1False() {
        assertTrue(obj.posNeg(1, -1, false));
    }
    
    @Test
    public void testNeg11False() {
        assertTrue(obj.posNeg(-1, 1, false));
    }
    
    @Test
    public void testNeg4Neg5True() {
        assertTrue(obj.posNeg(-4, -5, true));
    }
    
}
