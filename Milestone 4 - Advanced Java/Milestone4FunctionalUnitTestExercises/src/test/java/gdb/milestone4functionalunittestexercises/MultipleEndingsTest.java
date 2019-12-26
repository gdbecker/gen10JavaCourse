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

public class MultipleEndingsTest {
    
    MultipleEndings obj = new MultipleEndings();
    
    public MultipleEndingsTest() {
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
     * Test of multipleEndings method, of class MultipleEndings.
     */
    @Test
    public void testHello() {
        assertEquals("lololo", obj.multipleEndings("Hello"));
    }
    
    @Test
    public void testab() {
        assertEquals("ababab", obj.multipleEndings("ab"));
    }
    
    @Test
    public void testHi() {
        assertEquals("HiHiHi", obj.multipleEndings("Hi"));
    }
    
}
