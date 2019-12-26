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

public class NearHundredTest {
    
    NearHundred obj = new NearHundred();
    
    public NearHundredTest() {
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
     * Test of nearHundred method, of class NearHundred.
     */
    @Test
    public void test103() {
        assertTrue(obj.nearHundred(103));
    }
    
    @Test
    public void test90() {
        assertTrue(obj.nearHundred(90));
    }
    
    @Test
    public void test89() {
        assertFalse(obj.nearHundred(89));
    }
    
}
