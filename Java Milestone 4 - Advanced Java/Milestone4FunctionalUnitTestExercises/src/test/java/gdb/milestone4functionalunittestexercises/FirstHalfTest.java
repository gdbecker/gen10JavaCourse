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

public class FirstHalfTest {
    
    FirstHalf obj = new FirstHalf();
    
    public FirstHalfTest() {
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
     * Test of firstHalf method, of class FirstHalf.
     */
    @Test
    public void testWooHoo() {
        assertEquals("Woo", obj.firstHalf("WooHoo"));
    }
    
    @Test
    public void testHelloThere() {
        assertEquals("Hello", obj.firstHalf("HelloThere"));
    }
    
    @Test
    public void testabcdef() {
        assertEquals("abc", obj.firstHalf("abcdef"));
    }
    
}
