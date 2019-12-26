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

public class RotateLeftTest {
    
    RotateLeft obj = new RotateLeft();
    
    public RotateLeftTest() {
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
     * Test of rotateLeft method, of class RotateLeft.
     */
    @Test
    public void test123() {
        int[] input = {1, 2, 3};
        int[] expected = {2, 3, 1};
        assertEquals(expected, obj.rotateLeft(input));
    }
    
    @Test
    public void test5119() {
        int[] input = {5, 11, 9};
        int[] expected = {11, 9, 5};
        assertEquals(expected, obj.rotateLeft(input));
    }
    
    @Test
    public void test700() {
        int[] input = {7, 0, 0};
        int[] expected = {0, 0, 7};
        assertEquals(expected, obj.rotateLeft(input));
    }
    
}
