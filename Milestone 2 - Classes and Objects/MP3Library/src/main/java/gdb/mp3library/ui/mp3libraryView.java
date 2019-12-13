package gdb.mp3library.ui;

import gdb.mp3library.dto.mp3;
import java.util.List;

/**
 * Milestone 2 Assessment
 * @author garrettbecker
 */

public class mp3libraryView {
    private UserIO io;

    //Constructor for making this view
    public mp3libraryView(UserIO io) {
        this.io = io;
    }
    
    //Shows menu to the screen & gets user's choice of action
    public int showMenuGetSelection() {
        io.print("*** Welcome to your MP3 Collection ***");
        io.print("Please select the action you wish to do:");
        io.print("(1) Add a new MP3 Track");
        io.print("(2) Remove an MP3 Track");
        io.print("(3) Edit an Existing MP3 Track");
        io.print("(4) Show all MP3 Tracks in the Collection");
        io.print("(5) Display MP3 Track Info (search by title)");
        io.print("(6) Load an Existing Library into the Collection");
        io.print("(7) Copy MP3 Collection Info to a File");
        io.print("(8) Exit");
        
        return io.readInt("Please select from the above choices.", 1, 8);
    }
    
    //Ask user for info for creating a new MP3 track and return the new mp3 object
    //Option 1: add
    public mp3 getNewMP3() {
        String title = io.readString("Enter track title:");
        String releaseDate = io.readString("Enter release date:");
        String album = io.readString("Enter album name:");
        String artistName = io.readString("Enter track's artist:");
        String genre = io.readString("Enter track's genre:");
        String moreInfo = io.readString("Enter any personal notes or ratings:");
        
        mp3 currentMP3 = new mp3(title);
        
        currentMP3.setReleaseDate(releaseDate);
        currentMP3.setAlbum(album);
        currentMP3.setArtistName(artistName);
        currentMP3.setGenre(genre);
        currentMP3.setMoreInfo(moreInfo);
        
        return currentMP3;
    }
    
    //Option 1: add
    public void displayAddMP3Banner() {
        io.print("*** Add a new MP3 Track ***");
    }
    
    //Option 1: add
    public void displayAddMP3SuccessBanner() {
        io.print("New MP3 track was successfully created and added!");
        io.readString("Press Enter to go back to the main screen.");
    }
    
    //Option 2: remove
    public void displayRemoveMP3Banner() {
        io.print("*** Remove an MP3 Track ***");
    }
    
    //Option 2: remove
    public void displayRemoveSuccessBanner() {
        io.print("MP3 track was successfully removed from the collection.");
        io.readString("Press Enter to go back to the main screen.");
    }
    
    //Show info for track which user wishes to edit
    //Option 3: edit
    public void showMP3ToEdit(mp3 currentMP3) {
        if (currentMP3 != null) {
            io.print("Title: " + currentMP3.getTitle());
            io.print("Album: " + currentMP3.getAlbum());
            io.print("Artist: " + currentMP3.getArtistName());
            io.print("Release Date: " + currentMP3.getReleaseDate());
            io.print("Genre: " + currentMP3.getGenre());
            io.print("Your notes: " + currentMP3.getMoreInfo());
            io.print("");
        } else {
            io.print("No MP3 track found!");
        } 
        
        io.print("Press Enter to edit");
    }
    
    //Ask user for info for new info to replace on existing mp3 track
    //Option 3: edit
    public mp3 editMP3(mp3 currentMP3) {
        String title = io.readString("Enter track title:");
        String releaseDate = io.readString("Enter release date:");
        String album = io.readString("Enter album name:");
        String artistName = io.readString("Enter track's artist:");
        String genre = io.readString("Enter track's genre:");
        String moreInfo = io.readString("Enter any personal notes or ratings:");
        
        currentMP3.setTitle(title);
        currentMP3.setReleaseDate(releaseDate);
        currentMP3.setAlbum(album);
        currentMP3.setArtistName(artistName);
        currentMP3.setGenre(genre);
        currentMP3.setMoreInfo(moreInfo);
        
        return currentMP3;
    }
    
    //Option 3: edit
    public void displayEditMP3Banner() {
        io.print("*** Edit an Existing MP3 Track ***");
    }
    
    //Option 3: edit
    public void displayEditSuccessBanner() {
        io.print("MP3 track was successfully edited!");
        io.readString("Press Enter to go back to the main screen.");
    }
    
    //Display all of MP3 tracks in the collection
    //Option 4: display all tracks
    public void displayMP3List(List<mp3> mp3List) {
        for (mp3 currentMP3 : mp3List) {
            io.print("Title: " + currentMP3.getTitle());
            io.print("Album: " + currentMP3.getAlbum());
            io.print("Artist: " + currentMP3.getArtistName());
            io.print("Release Date: " + currentMP3.getReleaseDate());
            io.print("Genre: " + currentMP3.getGenre());
            io.print("Your notes: " + currentMP3.getMoreInfo());
            io.print("");
        }
        
        io.readString("Press Enter to go back to the main screen.");
    }
    
    //Option 4: display all tracks
    public void displayAllMP3TracksBanner() {
        io.print("*** Showing all MP3 Tracks in the Collection ***");
    }
    
    //Takes in an mp3 object and will display the track's info
    //Option 5: display single track
    public void displayMP3(mp3 currentMP3) {
        if (currentMP3 != null) {
            io.print("Title: " + currentMP3.getTitle());
            io.print("Album: " + currentMP3.getAlbum());
            io.print("Artist: " + currentMP3.getArtistName());
            io.print("Release Date: " + currentMP3.getReleaseDate());
            io.print("Genre: " + currentMP3.getGenre());
            io.print("Your notes: " + currentMP3.getMoreInfo());
            io.print("");
        } else {
            io.print("No MP3 track found!");
        }
        
        io.readString("Press Enter to go back to the main screen.");    
    }
    
    //Option 5: display single track
    public void displaySingleMP3TrackBanner() {
        io.print("*** Displaying MP3 Track Info ***");
    }
    
    //Option 6: load from library file 
    public void displayLoadBanner() {
        io.print("*** Loading an Existing Library into the Collection ***");
    }
    
    //Option 6: load from library file
    public void displayLoadSuccessBanner() {
        io.print("MP3 library successfully imported!");
        io.readString("Press Enter to go back to the main screen.");
    }
    
    //Option 7: write to library file 
    public void displayWriteBanner() {
        io.print("*** Copying MP3 Collection Info to a File ***");
    }
    
    //Option 7: write to library file
    public void displayWriteSuccessBanner() {
        io.print("MP3 library successfully copied!");
        io.readString("Press Enter to go back to the main screen.");
    }
    
    //For any option
    public String getTitleChoice() {
        return io.readString("Enter title of track:");
    }
    
    public void displayExitBanner() {
        io.print("Good bye!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
