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
public class CanHazTableTest {
    
    CanHazTable obj = new CanHazTable();
    
    public CanHazTableTest() {
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
     * Test of canHazTable method, of class CanHazTable.
     */
    @Test
    public void test5And10() {
        assertEquals(2, obj.canHazTable(5, 10));
    }
    
    @Test
    public void test5And2() {
        assertEquals(0, obj.canHazTable(5, 2));
    }
    
    @Test
    public void test5And5() {
        assertEquals(1, obj.canHazTable(5, 5));
    }
}
