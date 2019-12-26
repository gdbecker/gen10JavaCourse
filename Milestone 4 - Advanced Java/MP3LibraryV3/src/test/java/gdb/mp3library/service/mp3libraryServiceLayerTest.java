/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdb.mp3library.service;

import gdb.mp3library.dao.mp3libraryDao;
import gdb.mp3library.dao.mp3libraryDaoStubImpl;
import gdb.mp3library.dao.mp3libraryPersistenceException;
import gdb.mp3library.dto.mp3;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @date Wednesday December 18, 2019
 * @author garrettbecker
 */
public class mp3libraryServiceLayerTest {
    
    private mp3libraryServiceLayer service;
    
    public mp3libraryServiceLayerTest() {
        /*
        //Use the stub dao version for making the dao
        mp3libraryDao dao = new mp3libraryDaoStubImpl();
        
        //Create the tester service object for the purposes of testing
        service = new mp3libraryServiceLayerImpl(dao);
        */
        
        //Replacing above code with new code for Spring Dependency
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", mp3libraryServiceLayer.class);
    }
    
    //Don't need anything for these
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
     * Test of addMP3 method, of class mp3libraryServiceLayer.
     */
    @Test
    public void testAddMP3() throws Exception {
        //Make up a random mp3 object with all fields filled in
        mp3 myMP3 = new mp3("Rockets");
        myMP3.setReleaseDate("2013-11-23");
        myMP3.setAlbum("NotSure");
        myMP3.setArtistName("Nobody");
        myMP3.setGenre("Rock");
        myMP3.setMoreInfo("Love it");
        
        //Add it with the add method
        service.addMP3(myMP3);
        
        //Remember that the focus is on business rules, not on technical functionality
        //The DAO testing is checking the core details
    }
    
    @Test
    public void testAddMP3DuplicateTitle() throws Exception {
        //Make up a random mp3 object with all fields in and with same title
        //as the one in mp3libraryDaoStubImpl
        mp3 myMP3 = new mp3("Tester");
        myMP3.setReleaseDate("2015-07-01");
        myMP3.setAlbum("Someone to Love");
        myMP3.setArtistName("Polar Caps");
        myMP3.setGenre("Folk");
        myMP3.setMoreInfo("Chill");
        
        //Create error if the service tries to do this
        try {
            service.addMP3(myMP3);
            fail("Expected mp3libraryDuplicateTitleException was not thrown.");
        } catch (mp3libraryDuplicateTitleException e) { 
            return;
        }
    }
    
    @Test
    public void testAddMP3InvalidData() throws Exception {
        //Make up a random mp3 object with all fields in but with some info missing
        mp3 myMP3 = new mp3("Rockets");
        myMP3.setReleaseDate("");
        myMP3.setAlbum("Someone to Love");
        myMP3.setArtistName("Polar Caps");
        myMP3.setGenre("Folk");
        myMP3.setMoreInfo("Chill");
        
        //Create error if the service tries to do this
        try {
            service.addMP3(myMP3);
            fail("Expected mp3libraryDataValidationException was not thrown.");
        } catch (mp3libraryDataValidationException e) { 
            return;
        }
    }

    /**
     * Test of removeMP3 method, of class mp3libraryServiceLayer.
     */
    @Test
    public void testRemoveMP3() throws Exception {
        //Get the mp3 info from the stub file and make sure it's not null
        mp3 thisMP3 = service.displayMP3Info("Tester");
        assertNotNull(thisMP3);
        
        //Now try to delete something that wasn't there and test to make sure
        //that this is null
        thisMP3 = service.removeMP3("Something Else");
        assertNull(thisMP3);
    }

    /**
     * Test of editMP3 method, of class mp3libraryServiceLayer.
     */
    @Test
    public void testEditMP3() throws Exception {
        //Get the mp3 info from the stub file and make sure it's not null
        mp3 thisMP3 = service.displayMP3Info("Tester");
        assertNotNull(thisMP3);
        
        //Now make some edits to this mp3
        thisMP3.setAlbum("Dancing in the Rain");
        thisMP3.setMoreInfo("So gooood");
        
        //Now return this edited mp3 to the system
        service.editMP3(thisMP3);
    }

    /**
     * Test of showAllMP3 method, of class mp3libraryServiceLayer.
     */
    @Test
    public void testShowAllMP3() throws Exception {
        assertEquals(1, service.showAllMP3().size());
    }

    /**
     * Test of displayMP3Info method, of class mp3libraryServiceLayer.
     */
    @Test
    public void testDisplayMP3Info() throws Exception {
        //Get the mp3 info from the stub file and make sure it's not null
        mp3 thisMP3 = service.displayMP3Info("Tester");
        assertNotNull(thisMP3);
        
        //Now try to get something that wasn't there and test to make sure
        //that this is null
        thisMP3 = service.displayMP3Info("Something Else");
        assertNull(thisMP3);
    }

    /**
     * Test of loadMP3Library method, of class mp3libraryServiceLayer.
     */
    @Test
    public void testLoadMP3Library() throws Exception {
        //Do nothing
    }

    /**
     * Test of writeMP3Library method, of class mp3libraryServiceLayer.
     */
    @Test
    public void testWriteMP3Library() throws Exception {
        //Do nothing
    }
}
