package gdb.mp3library.service;

import gdb.mp3library.dao.mp3libraryPersistenceException;
import gdb.mp3library.dto.mp3;
import java.util.List;
import java.util.Map;

/**
 * @date Wednesday December 18, 2019
 * @author garrettbecker
 */

public interface mp3libraryServiceLayer {
    void addMP3(mp3 newMP3) throws
            mp3libraryDuplicateTitleException,
            mp3libraryDataValidationException,
            mp3libraryPersistenceException;
    
    mp3 removeMP3(String title) throws
            mp3libraryPersistenceException;
    
    void editMP3(mp3 newMP3) throws
            mp3libraryDuplicateTitleException,
            mp3libraryDataValidationException,
            mp3libraryPersistenceException;
    
    List<mp3> showAllMP3() throws
            mp3libraryPersistenceException;
    
    mp3 displayMP3Info(String title) throws
            mp3libraryPersistenceException;
    
    void loadMP3Library() throws 
            mp3libraryPersistenceException;
    
    void writeMP3Library() throws 
            mp3libraryPersistenceException;
    
    public List<mp3> getAllMP3WithinLastNYears(int n);
    
    public Map<String, List<mp3>> getAllMP3ByGenre(String genre);
    
    public List<mp3> getAllMP3ByArtistName(String artistName);
    
    public List<mp3> getAllMP3ByAlbum(String album);
    
    public double getAverageMP3Age();
    
    public List<mp3> getNewestMP3();
    
    public List<mp3> getOldestMP3();
    
    public double getAverageNumOfNotes();
}
