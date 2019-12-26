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

public class SpringSplosionTest {
    
    SpringSplosion obj = new SpringSplosion();
    
    public SpringSplosionTest() {
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
     * Test of stringSplosion method, of class SpringSplosion.
     */
    @Test
    public void testCode() {
        assertEquals("CCoCodCode", obj.stringSplosion("Code"));
    }
    
    @Test
    public void testabc() {
        assertEquals("aababc", obj.stringSplosion("abc"));
    }
    
    @Test
    public void testab() {
        assertEquals("aab", obj.stringSplosion("ab"));
    }
    
}
