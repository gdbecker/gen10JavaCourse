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
 * @date Monday December 16, 2019
 * @author garrettbecker
 */

public class GreatPartyTest {
    
    GreatParty party = new GreatParty();
    
    public GreatPartyTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
    /*
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
    */
    
    /**
     * Test of greatParty method, of class GreatParty.
     */

    @Test
    public void test30False() {
        assertFalse(party.greatParty(30, false));
    }
    
    @Test
    public void test50False() {
        assertTrue(party.greatParty(50, false));
    }
    
    @Test
    public void test70True() {
        assertTrue(party.greatParty(70, true));
    }
    
    @Test
    public void test39True() {
        assertFalse(party.greatParty(39, true));
    }
    
    @Test
    public void test39False() {
        assertFalse(party.greatParty(39, false));
    }
    
    @Test
    public void test40True() {
        assertTrue(party.greatParty(40, true));
    }
    
    @Test
    public void test40False() {
        assertTrue(party.greatParty(40, false));
    }
    
    @Test
    public void test60True() {
        assertTrue(party.greatParty(60, true));
    }
    
    @Test
    public void test60False() {
        assertTrue(party.greatParty(60, false));
    }
    
    @Test
    public void test61True() {
        assertTrue(party.greatParty(61, true));
    }
    
    @Test
    public void test61False() {
        assertFalse(party.greatParty(61, false));
    }
    
}
