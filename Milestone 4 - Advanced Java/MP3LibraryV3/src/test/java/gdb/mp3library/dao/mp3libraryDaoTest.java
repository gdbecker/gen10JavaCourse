/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdb.mp3library.dao;

import gdb.mp3library.dto.mp3;
import java.util.List;
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

public class mp3libraryDaoTest {
    
    private mp3libraryDao dao = new mp3libraryDaoFileImpl();
    
    public mp3libraryDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    //Very important to have set up since you need to get in a known good state
    //before running tests on stateful code
    @BeforeEach
    public void setUp() throws Exception {
        List<mp3> listMP3 = dao.showAllMP3();
        for (mp3 m : listMP3) {
            dao.removeMP3(m.getTitle());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addMP3 method, of class mp3libraryDao.
     */
    @Test
    public void testAddMP3() throws Exception {
        //Make up a random mp3 object with all fields filled in
        mp3 myMP3 = new mp3("Tester");
        myMP3.setReleaseDate("2013-11-23");
        myMP3.setAlbum("NotSure");
        myMP3.setArtistName("Nobody");
        myMP3.setGenre("Rock");
        myMP3.setMoreInfo("Love it");
        
        //Add it to memory
        dao.addMP3(myMP3.getTitle(), myMP3);
        
        //Now try to get it from memory
        mp3 gettingMP3 = dao.displayMP3Info(myMP3.getTitle());
        
        //Check to make sure that these are equal
        assertEquals(myMP3, gettingMP3);
    }

    /**
     * Test of removeMP3 method, of class mp3libraryDao.
     */
    @Test
    public void testRemoveMP3() throws Exception {
        //Make up a random mp3 object with all fields filled in
        mp3 myMP3 = new mp3("Tester");
        myMP3.setReleaseDate("2013-11-23");
        myMP3.setAlbum("NotSure");
        myMP3.setArtistName("Nobody");
        myMP3.setGenre("Rock");
        myMP3.setMoreInfo("Love it");
        
        //Add it to memory
        dao.addMP3(myMP3.getTitle(), myMP3);
        
        //Make up another random mp3 object with all fields filled in
        mp3 anotherMP3 = new mp3("Song I Love");
        anotherMP3.setReleaseDate("2016-02-08");
        anotherMP3.setAlbum("Songs of Today");
        anotherMP3.setArtistName("Basket Weaver");
        anotherMP3.setGenre("Folk");
        anotherMP3.setMoreInfo("Chill");
        
        //Add it to memory
        dao.addMP3(anotherMP3.getTitle(), anotherMP3);
        
        //Now check to make sure that the method is removing these mp3 objects
        dao.removeMP3(myMP3.getTitle());
        assertEquals(1, dao.showAllMP3().size());
        assertNull(dao.displayMP3Info(myMP3.getTitle()));
        
        dao.removeMP3(anotherMP3.getTitle());
        assertEquals(0, dao.showAllMP3().size());
        assertNull(dao.displayMP3Info(anotherMP3.getTitle()));
    }

    /**
     * Test of editMP3 method, of class mp3libraryDao.
     */
    @Test
    public void testEditMP3() throws Exception {
        //Make up a random mp3 object with all fields filled in
        mp3 myMP3 = new mp3("Tester");
        myMP3.setReleaseDate("2013-11-23");
        myMP3.setAlbum("NotSure");
        myMP3.setArtistName("Nobody");
        myMP3.setGenre("Rock");
        myMP3.setMoreInfo("Love it");
        
        //Add it to memory
        dao.addMP3(myMP3.getTitle(), myMP3);
        
        //Now try getting this mp3 from memory
        mp3 fromDao = dao.displayMP3Info(myMP3.getTitle());
        
        //Edit a couple of fields
        fromDao.setAlbum("Songs for the Road");
        fromDao.setReleaseDate("2014-10-12");
        
        //Put it back into memory
        dao.editMP3(myMP3.getTitle(), myMP3);
        
        //Make sure that these two pointers are pointing to the same object
        //title did not change
        assertEquals(myMP3, fromDao);
        
        
    }

    /**
     * Test of showAllMP3 method, of class mp3libraryDao.
     */
    @Test
    public void testShowAllMP3() throws Exception {
        //Make up a random mp3 object with all fields filled in
        mp3 myMP3 = new mp3("Tester");
        myMP3.setReleaseDate("2013-11-23");
        myMP3.setAlbum("NotSure");
        myMP3.setArtistName("Nobody");
        myMP3.setGenre("Rock");
        myMP3.setMoreInfo("Love it");
        
        //Add it to memory
        dao.addMP3(myMP3.getTitle(), myMP3);
        
        //Make up another random mp3 object with all fields filled in
        mp3 anotherMP3 = new mp3("Song I Love");
        anotherMP3.setReleaseDate("2016-02-08");
        anotherMP3.setAlbum("Songs of Today");
        anotherMP3.setArtistName("Basket Weaver");
        anotherMP3.setGenre("Folk");
        anotherMP3.setMoreInfo("Chill");
        
        //Add it to memory
        dao.addMP3(anotherMP3.getTitle(), anotherMP3);
        
        //Make sure that there are two mp3 objects in there
        assertEquals(2, dao.showAllMP3().size());
    }

    /**
     * Test of displayMP3Info method, of class mp3libraryDao.
     */
    @Test
    public void testDisplayMP3Info() throws Exception {
        //Make up a random mp3 object with all fields filled in
        mp3 myMP3 = new mp3("Tester");
        myMP3.setReleaseDate("2013-11-23");
        myMP3.setAlbum("NotSure");
        myMP3.setArtistName("Nobody");
        myMP3.setGenre("Rock");
        myMP3.setMoreInfo("Love it");
        
        //Add it to memory
        dao.addMP3(myMP3.getTitle(), myMP3);
        
        //Now try to get it from memory
        mp3 gettingMP3 = dao.displayMP3Info(myMP3.getTitle());
        
        //Check to make sure that these are equal
        assertEquals(myMP3, gettingMP3);
    }

    /**
     * Test of loadMP3Library method, of class mp3libraryDao.
     */
    @Test
    public void testLoadMP3Library() throws Exception {
        //Do nothing
    }

    /**
     * Test of writeMP3Library method, of class mp3libraryDao.
     */
    @Test
    public void testWriteMP3Library() throws Exception {
        //Do nothing
    }
    
}
