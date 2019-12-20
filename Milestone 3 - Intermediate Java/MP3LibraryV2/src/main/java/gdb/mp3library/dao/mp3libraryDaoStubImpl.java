package gdb.mp3library.dao;

import gdb.mp3library.dto.mp3;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @date Wednesday December 18, 2019
 * @author garrettbecker
 */

//This is basically a dumbed down version of the AddressBookDaoFileImpl file
//This exists for the purpose of testing that these methods work
public class mp3libraryDaoStubImpl implements mp3libraryDao {
    
    mp3 onlyMP3;
    List<mp3> listMP3 = new ArrayList<>();
    
    //Create a default constructor with info filled in so that there's data
    //for the service test to use
    public mp3libraryDaoStubImpl() {
        onlyMP3 = new mp3("Tester");
        LocalDate releaseDate = LocalDate.parse("2013-12-12", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        onlyMP3.setReleaseDate(releaseDate);
        onlyMP3.setAlbum("Songs of the Air");
        onlyMP3.setArtistName("Frank Castles");
        onlyMP3.setGenre("Rock");
        onlyMP3.setMoreInfo("Fave fave");
        
        listMP3.add(onlyMP3);
    }

    @Override
    public mp3 addMP3(String title, mp3 newMP3) throws mp3libraryPersistenceException {
        //We don't care what this method does since this is being used to test the 
        //service layer.
        //Ensure that the onlyMP3 object gets returned
        if (title.equals(onlyMP3.getTitle())) {
            return onlyMP3;
        } else {
            return null;
        }
    }

    @Override
    public mp3 removeMP3(String title) throws mp3libraryPersistenceException {
        //We don't care what this method does since this is being used to test the 
        //service layer.
        //Ensure that the onlyMP3 object gets returned
        if (title.equals(onlyMP3.getTitle())) {
            return onlyMP3;
        } else {
            return null;
        }
    }

    @Override
    public mp3 editMP3(String title, mp3 newMP3) throws mp3libraryPersistenceException {
        //We don't care what this method does since this is being used to test the 
        //service layer.
        //Ensure that the onlyMP3 object gets returned
        if (title.equals(onlyMP3.getTitle())) {
            return onlyMP3;
        } else {
            return null;
        }
    }

    @Override
    public List<mp3> showAllMP3() throws mp3libraryPersistenceException {
        return listMP3;
    }

    @Override
    public mp3 displayMP3Info(String title) throws mp3libraryPersistenceException {
        //We don't care what this method does since this is being used to test the 
        //service layer.
        //Ensure that the onlyMP3 object gets returned
        if (title.equals(onlyMP3.getTitle())) {
            return onlyMP3;
        } else {
            return null;
        }
    }

    @Override
    public void loadMP3Library() throws mp3libraryPersistenceException {
        //Do nothing
    }

    @Override
    public void writeMP3Library() throws mp3libraryPersistenceException {
        //Do nothing
    }

    @Override
    public List<mp3> getAllMP3WithinLastNYears(int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, List<mp3>> getAllMP3ByGenre(String genre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<mp3> getAllMP3ByArtistName(String artistName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<mp3> getAllMP3ByAlbum(String album) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getAverageMP3Age() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<mp3> getNewestMP3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<mp3> getOldestMP3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getAverageNumOfNotes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
