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

public class LongInMiddleTest {
    
    LongInMiddle obj = new LongInMiddle();
    
    public LongInMiddleTest() {
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
     * Test of longInMiddle method, of class LongInMiddle.
     */
    @Test
    public void testHelloHi() {
        assertEquals("hiHellohi", obj.longInMiddle("Hello", "hi"));
    }
    
    @Test
    public void testHiHello() {
        assertEquals("hiHellohi", obj.longInMiddle("hi", "Hello"));
    }
    
    @Test
    public void testaaab() {
        assertEquals("baaab", obj.longInMiddle("aaa", "b"));
    }
    
}
