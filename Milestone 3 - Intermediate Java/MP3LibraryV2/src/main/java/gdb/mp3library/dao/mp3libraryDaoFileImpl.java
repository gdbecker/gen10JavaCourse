package gdb.mp3library.dao;

import gdb.mp3library.dto.mp3;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Milestone 2 Assessment
 * @author garrettbecker
 */

public class mp3libraryDaoFileImpl implements mp3libraryDao{
    private Map<String, mp3> mp3Collection = new HashMap<>();
    public static final String FILE = "mp3Library.txt";
    public static final String DELIMITER = "::";

    //dao will take in title and mp3 object and add them to the Map
    //Option 1: add
    @Override
    public mp3 addMP3(String title, mp3 myMP3) throws mp3libraryPersistenceException {
        mp3 newMP3 = mp3Collection.put(title, myMP3);
        return newMP3;
    }

    //dao will take in a title and remove the associated mp3 from the Map
    //Option 2: remove
    @Override
    public mp3 removeMP3(String title) throws mp3libraryPersistenceException {
        mp3 removedMP3 = mp3Collection.remove(title);
        return removedMP3;
    }

    //dao will take in a title and edited mp3 object and update the Map
    //Option 3: edit
    @Override
    public mp3 editMP3(String title, mp3 newMP3) throws mp3libraryPersistenceException {
        mp3 editedMP3 = mp3Collection.put(title, newMP3);
        return editedMP3;
    }

    //No parameters. dao will collect all mp3 objects and return them as a list
    //Option 4: display all tracks
    @Override
    public List<mp3> showAllMP3() throws mp3libraryPersistenceException {
        return new ArrayList<mp3>(mp3Collection.values());
    }

    //dao will take in a title and return the mp3 object with that title
    //Option 5: display single track
    //(Also used in Option 3 to display track to edit)
    @Override
    public mp3 displayMP3Info(String title) throws mp3libraryPersistenceException {
        return mp3Collection.get(title);
    }

    //Part of loading from library file (before menu pops up)
    private mp3 unmarshallMP3(String mp3AsText){
        // mp3AsText is expecting a line read in from our file.
        String[] mp3Tokens = mp3AsText.split(DELIMITER);

        //Get title from tokens and create new mp3 object
        String title = mp3Tokens[0];
        mp3 mp3FromFile = new mp3(title);
        
        //Get remaining properties to complete mp3 object
        //Index 1 - release date
        mp3FromFile.setReleaseDate(mp3Tokens[1]);
        
        //Index 2 - album
        mp3FromFile.setAlbum(mp3Tokens[2]);
        
        //Index 3 - artist
        mp3FromFile.setArtistName(mp3Tokens[3]);
        
        //Index 4 - genre
        mp3FromFile.setGenre(mp3Tokens[4]);
        
        //Index 5 - more info
        mp3FromFile.setMoreInfo(mp3Tokens[5]);
     
        //Return new mp3 object
        return mp3FromFile;
    }
    
    //load from library file (before menu pops up)
    @Override
    public void loadMP3Library() throws mp3libraryPersistenceException {
        Scanner sc;
        
        try {
            //Create Scanner for reading the file
            sc = new Scanner(new BufferedReader(new FileReader(FILE)));
        } catch (FileNotFoundException e) {
            throw new mp3libraryPersistenceException("-_- Could not load MP3 Library data into memory.", e);
        }
        
        //currentLine holds the most recent line read from the file
        String currentLine;
        
        //currentMP3 holds the most recent address unmarshalled
        mp3 currentMP3;
        
        //Go through the FILE line by line, decoding each line into an 
        //mp3 object by calling the unmarshallMP3 method.
        //Process while we have more lines in the file
        while (sc.hasNextLine()) {
            //Get the next line in the file
            currentLine = sc.nextLine();
            
            //Unmarshall the line into an mp3
            currentMP3 = unmarshallMP3(currentLine);
            
            //Putting all mp3 objects into the Map in memory
            mp3Collection.put(currentMP3.getTitle(), currentMP3);
        }
    }

    //Part of writing memory to file (after program completes)
    private String marshallMP3(mp3 anMP3) {
        //Turning an mp3 object into a String to be printed into the file
        //Start with title and then add the rest of the object's info
        String mp3AsText = anMP3.getTitle() + DELIMITER;
        
        //Then add the rest of the object's info
        mp3AsText += anMP3.getReleaseDate() + DELIMITER;
        mp3AsText += anMP3.getAlbum() + DELIMITER;
        mp3AsText += anMP3.getArtistName() + DELIMITER;
        mp3AsText += anMP3.getGenre() + DELIMITER;
        mp3AsText += anMP3.getMoreInfo() + DELIMITER;

        return mp3AsText;
    }
    
    //write to library file  (after program completes)
    @Override
    public void writeMP3Library() throws mp3libraryPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(FILE));
        } catch (IOException e) {
            throw new mp3libraryPersistenceException("Could not save data.", e);
        }

        //write out mp3 objects to the file
        String mp3AsText;
        List<mp3> mp3List = this.showAllMP3(); //getting all mp3 objects from Map in memory
        for (mp3 currentMP3 : mp3List) {
            //make an mp3 object into a String using the above method
            mp3AsText = marshallMP3(currentMP3);
            out.println(mp3AsText);
            out.flush(); //making PrintWriter go to the next line
        }
       
        out.close();
    }
    
}
