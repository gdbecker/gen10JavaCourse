package gdb.mp3library.dao;

import gdb.mp3library.dto.mp3;
import java.util.List;
import java.util.Map;

/**
 * Milestone 2 Assessment
 * Also part of M3 exercises 
 * @author garrettbecker
 */

public interface mp3libraryDao {
    
    //Version 1.0 Base Features
    /**
     * Main function: create a new MP3 object to add to the collection.
     * 
     * Option 1 on menu
     * @param title title attribute of the mp3 object to add to collection
     * @param newMP3 mp3 object to add to the collection 
     * 
     * @return mp3 object to add to collection (or null if there's no title
     * associated with mp3
     */
    mp3 addMP3(String title, mp3 newMP3) throws mp3libraryPersistenceException;
    
    /**
     * Main function: remove an MP3 object from the collection
     * 
     * Option 2 on menu
     * @param title title attribute of the mp3 object to remove from collection
     * 
     * @return mp3 object to remove from collection (or null if not found)
     */
    mp3 removeMP3(String title) throws mp3libraryPersistenceException;
    
    /**
     * Main function: edit an existing MP3 object from the collection
     * 
     * Option 3 on menu
     * @param title title attribute of the mp3 object to remove from collection
     * @param newMP3 mp3 object already in the system 
     * 
     * @return edited mp3 object (or null if one wasn't found originally)
     */
    mp3 editMP3(String title, mp3 newMP3) throws mp3libraryPersistenceException;
    
    /**
     * Main function: list all existing MP3 objects in the collection
     * 
     * Option 4 on menu
     * @return String array containing all of the mp3's in the collection
     */
    List<mp3> showAllMP3() throws mp3libraryPersistenceException;
    
    /**
     * Main function: display info for a specific mp3 from the collection by 
     * searching by title
     * 
     * Option 5 on menu
     * @param title title attribute of the mp3 object to remove from collection
     * @param newMP3 mp3 object already in the system 
     * 
     * @return edited mp3 object (or null if one wasn't found originally)
     */
    mp3 displayMP3Info(String title) throws mp3libraryPersistenceException;
    
    /**
     * Main function: import a file containing mp3 objects to add to collection
     * 
     * New mp3 objects will be added to collection in memory 
     */
    void loadMP3Library() throws mp3libraryPersistenceException;
    
    /**
     * Main function: write a file containing mp3 objects from the collection
     * 
     * New mp3 objects will be written from collection in memory to a file
     */
    void writeMP3Library() throws mp3libraryPersistenceException;
    
    //Version 2.0 Additional Features
    /**
     * Find all MP3s released in the last N years
     * Find all the MP3s in a given genre
     * Find all the MP3s by a given artist
     * When searching by genre, the MP3s should be sorted into separate data structures by artist.
     * Find all the MP3s released on a particular album
     * Find the average age of the MP3s in the collection
     * Find the newest MP3 in your collection
     * Find the oldest MP3 in your collection
     * Find the average number of notes associated with MP3s in your collection
     */
    
    public List<mp3> getAllMP3WithinLastNYears(int n);
    
    public Map<String, List<mp3>> getAllMP3ByGenre(String genre);
    
    public List<mp3> getAllMP3ByArtistName(String artistName);
    
    public List<mp3> getAllMP3ByAlbum(String album);
    
    public double getAverageMP3Age();
    
    public List<mp3> getNewestMP3();
    
    public List<mp3> getOldestMP3();
    
    public double getAverageNumOfNotes();
    
}
