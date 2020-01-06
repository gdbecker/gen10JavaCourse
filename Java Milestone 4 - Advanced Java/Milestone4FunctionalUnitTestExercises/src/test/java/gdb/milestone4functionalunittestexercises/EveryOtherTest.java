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

public class EveryOtherTest {
    
    EveryOther obj = new EveryOther();
    
    public EveryOtherTest() {
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
     * Test of everyOther method, of class EveryOther.
     */
    @Test
    public void testHello() {
        assertEquals("Hlo", obj.everyOther("Hello"));
    }
    
    @Test
    public void testHi() {
        assertEquals("H", obj.everyOther("Hi"));
    }
    
    @Test
    public void testHeeololeo() {
        assertEquals("Hello", obj.everyOther("Heeololeo"));
    }
    
}
