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

public class InsertWordTest {
    
    InsertWord obj = new InsertWord();
    
    public InsertWordTest() {
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
     * Test of insertWord method, of class InsertWord.
     */
    @Test
    public void testYay() {
        String expected = "<<Yay>>";
        assertEquals(expected, obj.insertWord("<<>>", "Yay"));
    }
    
    @Test
    public void testWooHoo() {
        String expected = "<<WooHoo>>";
        assertEquals(expected, obj.insertWord("<<>>", "WooHoo"));
    }
    
    @Test
    public void testWord() {
        String expected = "[[word]]";
        assertEquals(expected, obj.insertWord("[[]]", "word"));
    }
    
}
