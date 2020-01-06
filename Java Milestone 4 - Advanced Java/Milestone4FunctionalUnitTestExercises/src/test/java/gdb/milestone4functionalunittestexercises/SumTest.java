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

public class SumTest {
    
    Sum obj = new Sum();
    
    public SumTest() {
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
     * Test of sum method, of class Sum.
     */
    @Test
    public void test123() {
        int[] a = {1, 2, 3};
        assertEquals(6, obj.sum(a));
    }
    
    @Test
    public void test5112() {
        int[] a = {5, 11, 2};
        assertEquals(18, obj.sum(a));
    }
    
    @Test
    public void test700() {
        int[] a = {7, 0, 0};
        assertEquals(7, obj.sum(a));
    }
    
}
