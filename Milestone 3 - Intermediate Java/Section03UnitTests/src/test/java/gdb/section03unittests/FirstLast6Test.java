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
public class FirstLast6Test {
    
    FirstLast6 obj = new FirstLast6();
    
    public FirstLast6Test() {
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
     * Tests of firstLast6 method, of class FirstLast6.
     */
    @Test
    public void test1And2And6() {
        int[] numbers = {1, 2, 6};
        assertTrue(obj.firstLast6(numbers));
    }
    
    @Test
    public void test6And1And2And3() {
        int[] numbers = {6, 1, 2, 3};
        assertTrue(obj.firstLast6(numbers));
    }
    
    @Test
    public void test13And6And1And2And3() {
        int[] numbers = {13, 6, 1, 2, 3};
        assertFalse(obj.firstLast6(numbers));
    }
    
}
