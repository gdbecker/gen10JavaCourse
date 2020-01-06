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

public class Makes10Test {
    
    Makes10 obj = new Makes10();
    
    public Makes10Test() {
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
     * Test of makes10 method, of class Makes10.
     */
    @Test
    public void test910() {
        assertTrue(obj.makes10(9, 10));
    }
    
    @Test
    public void test99() {
        assertFalse(obj.makes10(9, 9));
    }
    
    @Test
    public void test19() {
        assertTrue(obj.makes10(1, 9));
    }
    
}
