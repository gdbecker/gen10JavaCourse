package gdb.mp3library.ui;

import gdb.mp3library.dao.mp3libraryDaoException;
import gdb.mp3library.dto.mp3;
import java.util.InputMismatchException;
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
    
    public void displayWelcomeBanner() {
        io.print("*** Welcome to your MP3 Collection ***");
        io.print("");
    }
    //Shows menu to the screen & gets user's choice of action
    public int showMenuGetSelection() {
        int selection = 0;
        io.print("*** Main Menu ***");
        io.print("Please select the action you wish to do:");
        io.print("(1) Add a new MP3 Track");
        io.print("(2) Remove an MP3 Track");
        io.print("(3) Edit an Existing MP3 Track");
        io.print("(4) Show all MP3 Tracks in the Collection");
        io.print("(5) Display MP3 Track Info (search by title)");
        io.print("(6) Exit");
        
        try {
            selection = io.readInt("Please select from the above choices.", 1, 6);
        } catch (InputMismatchException e) {
            io.print("Bad input! Exiting program.");
        }
        
        return selection;
    }
    
    public int showAddRemoveEditMenuGetSelection() {
        int selection = 0;
        io.print("");
        io.print("Would you like to add, remove, or edit another track?");
        io.print("Please select the action you wish to do:");
        io.print("(1) Add a new MP3 Track");
        io.print("(2) Remove an MP3 Track");
        io.print("(3) Edit an Existing MP3 Track");
        io.print("(4) Go back to the Main Menu");
        
        try {
            selection = io.readInt("Please select from the above choices.", 1, 4);
        } catch (InputMismatchException e) {
            io.print("Bad input!");
        }
        
        return selection;
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
    }
    
    //Option 2: remove
    public void displayRemoveMP3Banner() {
        io.print("*** Remove an MP3 Track ***");
    }
    
    //Option 2: remove
    public void displayRemoveSuccessBanner() {
        io.readString("MP3 track was successfully removed from the collection. Please press Enter.");
    }
    
    //Show info for track which user wishes to edit
    //Option 3: edit
    public void showMP3ToEdit(mp3 currentMP3) {
        if (currentMP3 != null) {
            io.print("Track found! Info is below:");
            io.print("");
            io.print("Title: " + currentMP3.getTitle());
            io.print("Album: " + currentMP3.getAlbum());
            io.print("Artist: " + currentMP3.getArtistName());
            io.print("Release Date: " + currentMP3.getReleaseDate());
            io.print("Genre: " + currentMP3.getGenre());
            io.print("Your notes: " + currentMP3.getMoreInfo());
        } else {
            io.print("No MP3 track found! Please press Enter.");
        } 
    }
    
    //Ask user for info for new info to replace on existing mp3 track
    //Option 3: edit
    public mp3 editMP3(mp3 currentMP3) {
        int selection = 0;
        boolean keepGoing = true;
        
        mp3 newMP3 = new mp3(currentMP3.getTitle());
        newMP3.setReleaseDate(currentMP3.getReleaseDate());
        newMP3.setAlbum(currentMP3.getAlbum());
        newMP3.setArtistName(currentMP3.getArtistName());
        newMP3.setGenre(currentMP3.getGenre());
        newMP3.setMoreInfo(currentMP3.getMoreInfo());
        
        while (keepGoing) {
            io.print("\nSelect which field you would like to edit:");
            io.print("(1) Title");
            io.print("(2) Release Date");
            io.print("(3) Album");
            io.print("(4) Artist");
            io.print("(5) Genre");
            io.print("(6) Additional Info");
            io.print("(7) Exit");
            
            try {
                selection = io.readInt("Your selection:", 1, 7);
            } catch (InputMismatchException e) {
                io.print("Bad input!");
                break;
            }
            
            switch (selection) {
                case 1:
                    String title = io.readString("Enter new track title:");
                    newMP3.setTitle(title);
                    break;
                case 2:
                    String releaseDate = io.readString("Enter new release date:");
                    newMP3.setReleaseDate(releaseDate);
                    break;
                case 3:
                    String album = io.readString("Enter new album name:");
                    newMP3.setAlbum(album);
                    break;
                case 4:
                    String artistName = io.readString("Enter new track's artist:");
                    newMP3.setArtistName(artistName);
                    break;
                case 5:
                    String genre = io.readString("Enter new track genre:");
                    newMP3.setGenre(genre);
                    break;
                case 6:
                    String moreInfo = io.readString("Enter any personal notes or ratings:");
                    newMP3.setMoreInfo(moreInfo);
                    break;
                case 7:
                    keepGoing = false;
                    break;
                default:
                    io.print("-- Unkown input --");    
            }
            
            io.readString("MP3 track was successfully edited! Please press Enter.");
        }
        
        return newMP3;
    }
    
    //Option 3: edit
    public void displayEditMP3Banner() {
        io.print("\n*** Edit an Existing MP3 Track ***");
    }
    
    //Option 3: edit
    public void displayEditSuccessBanner() {
        io.readString("MP3 track was successfully edited! Please press Enter.");
    }
    
    //Error handling: cannot find mp3 file
    public void displayErrorBanner() {
        io.readString("ERROR: No MP3 track found! Please press Enter.");
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
            io.print("\n*** Displaying Track Info ***");
            io.print("Title: " + currentMP3.getTitle());
            io.print("Album: " + currentMP3.getAlbum());
            io.print("Artist: " + currentMP3.getArtistName());
            io.print("Release Date: " + currentMP3.getReleaseDate());
            io.print("Genre: " + currentMP3.getGenre());
            io.print("Your notes: " + currentMP3.getMoreInfo());
            io.print("");
            io.print("Please press Enter.");
        } else {
            io.print("No MP3 track found! Please press Enter.");
        }
        
        io.readString("Press Enter to go back to the main screen.");    
    }
    
    //Load from library file (before menu pops up)
    public void displayLoadSuccessBanner() {
        io.print("*** MP3 library successfully imported! ***");
        io.print("");
    }
    
    //Write to library file  (after program completes)
    public void displayWriteSuccessBanner() {
        io.print("");
        io.print("*** MP3 library successfully copied to the file! ***");
    }
    
    //For any option, get user input of track's title
    public String getTitleChoice() {
        return io.readString("Enter title of track:");
    }
    
    public void displayExitBanner() {
        io.print("");
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
