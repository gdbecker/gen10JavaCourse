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

public class SkipSumTest {
    
    SkipSum obj = new SkipSum();
    
    public SkipSumTest() {
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
     * Test of skipSum method, of class SkipSum.
     */
    @Test
    public void test34() {
        assertEquals(7, obj.skipSum(3, 4));
    }
    
    @Test
    public void test94() {
        assertEquals(20, obj.skipSum(9, 4));
    }
    
    @Test
    public void test1011() {
        assertEquals(21, obj.skipSum(10, 11));
    }
    
}
