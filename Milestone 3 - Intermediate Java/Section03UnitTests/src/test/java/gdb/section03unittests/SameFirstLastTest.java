/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdb.section03unittests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @date Wednesday December 18, 2019
 * @author garrettbecker
 */
public class SameFirstLastTest {
    
    SameFirstLast obj = new SameFirstLast();
    
    public SameFirstLastTest() {
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
     * Tests of sameFirstLast method, of class SameFirstLast.
     */
    @Test
    public void test1And2And3() {
        int[] numbers = {1, 2, 3};
        assertFalse(obj.sameFirstLast(numbers));
    }
    
    @Test
    public void test1And2And3And1() {
        int[] numbers = {1, 2, 3, 1};
        assertTrue(obj.sameFirstLast(numbers));
    }
    
    @Test
    public void test1And2And1() {
        int[] numbers = {1, 2, 1};
        assertTrue(obj.sameFirstLast(numbers));
    }
    
}
