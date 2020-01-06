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

public class DoubleXTest {
    
    DoubleX obj = new DoubleX();
    
    public DoubleXTest() {
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
     * Test of doubleX method, of class DoubleX.
     */
    @Test
    public void testaxxbb() {
        assertTrue(obj.doubleX("axxbb"));
    }
    
    @Test
    public void testaxaxxax() {
        assertFalse(obj.doubleX("axaxxax"));
    }
    
    @Test
    public void testxxxxx() {
        assertTrue(obj.doubleX("xxxxx"));
    }
    
}
