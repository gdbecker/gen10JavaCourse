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

public class TrimOneTest {
    
    TrimOne obj = new TrimOne();
    
    public TrimOneTest() {
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
     * Test of trimOne method, of class TrimOne.
     */
    @Test
    public void testHello() {
        assertEquals("ell", obj.trimOne("Hello"));
    }
    
    @Test
    public void testJava() {
        assertEquals("av", obj.trimOne("java"));
    }
    
    @Test
    public void testCoding() {
        assertEquals("odin", obj.trimOne("coding"));
    }
    
}
