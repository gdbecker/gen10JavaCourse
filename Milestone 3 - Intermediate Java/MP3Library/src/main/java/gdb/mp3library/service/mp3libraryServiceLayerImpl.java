package gdb.mp3library.service;

import gdb.mp3library.dao.mp3libraryDao;
import gdb.mp3library.dao.mp3libraryPersistenceException;
import gdb.mp3library.dto.mp3;
import java.util.List;

/**
 * @date Wednesday December 18, 2019
 * @author garrettbecker
 */

public class mp3libraryServiceLayerImpl implements mp3libraryServiceLayer {
    
    mp3libraryDao dao;
    
    //Constructor - needs to incorporate the dao
    public mp3libraryServiceLayerImpl(mp3libraryDao dao) {
        this.dao = dao;
    }
    
    public void validateMP3Data(mp3 MP3) throws mp3libraryDataValidationException {
        if (MP3.getTitle() == null
                || MP3.getTitle().trim().length() == 0
                || MP3.getReleaseDate() == null
                || MP3.getReleaseDate().trim().length() == 0
                || MP3.getAlbum() == null
                || MP3.getAlbum().trim().length() == 0
                || MP3.getArtistName() == null
                || MP3.getArtistName().trim().length() == 0
                || MP3.getGenre() == null
                || MP3.getGenre().trim().length() == 0)
                {
            throw new mp3libraryDataValidationException("ERROR: All fields are required.");
        }
    }

    @Override
    public void addMP3(mp3 newMP3) throws mp3libraryDuplicateTitleException, mp3libraryDataValidationException, mp3libraryPersistenceException {
        //Check first to make sure there aren't any mp3 duplicates
        if(dao.displayMP3Info(newMP3.getTitle()) != null) {
            throw new mp3libraryDuplicateTitleException("ERROR: Could not create MP3. An MP3 with title " + newMP3.getTitle() + " already exists.");
        }
        
        //Validate the fields of the mp3 being inputted
        validateMP3Data(newMP3);
        
        //Passed the business checks rules. Now add this address to the system
        dao.addMP3(newMP3.getTitle(), newMP3);
    }

    @Override
    public mp3 removeMP3(String title) throws mp3libraryPersistenceException {
        mp3 removedMP3 = dao.removeMP3(title);
        return removedMP3;
    }

    @Override
    public void editMP3(mp3 newMP3) throws mp3libraryDuplicateTitleException, mp3libraryDataValidationException, mp3libraryPersistenceException {
        //Validate the fields of the mp3 being inputted
        validateMP3Data(newMP3);
        
        //Passed the business checks rules. Now add this address to the system
        dao.addMP3(newMP3.getTitle(), newMP3);
    }

    @Override
    public List<mp3> showAllMP3() throws mp3libraryPersistenceException {
        return dao.showAllMP3();
    }

    @Override
    public mp3 displayMP3Info(String title) throws mp3libraryPersistenceException {
        return dao.displayMP3Info(title);
    }

    @Override
    public void loadMP3Library() throws mp3libraryPersistenceException {
        dao.loadMP3Library();
    }

    @Override
    public void writeMP3Library() throws mp3libraryPersistenceException {
        dao.writeMP3Library();
    }
    
}
