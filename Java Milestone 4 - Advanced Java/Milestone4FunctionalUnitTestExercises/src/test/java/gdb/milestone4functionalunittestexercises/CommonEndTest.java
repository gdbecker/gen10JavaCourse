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

public class CommonEndTest {
    
    CommonEnd obj = new CommonEnd();
    
    public CommonEndTest() {
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
     * Test of commonEnd method, of class CommonEnd.
     */
    @Test
    public void test123And73() {
        int[] a = {1, 2, 3};
        int[] b = {7, 3};
        assertTrue(obj.commonEnd(a, b));
    }
    
    @Test
    public void test123And732() {
        int[] a = {1, 2, 3};
        int[] b = {7, 3, 2};
        assertFalse(obj.commonEnd(a, b));
    }
    
    @Test
    public void test123And13() {
        int[] a = {1, 2, 3};
        int[] b = {1, 3};
        assertTrue(obj.commonEnd(a, b));
    }
    
}
