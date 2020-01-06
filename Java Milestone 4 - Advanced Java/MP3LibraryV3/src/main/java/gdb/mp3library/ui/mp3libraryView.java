package gdb.mp3library.ui;

import gdb.mp3library.dao.mp3libraryPersistenceException;
import gdb.mp3library.dto.mp3;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        io.print("(6) Display all MP3 Tracks within last [ ] years");
        io.print("(7) Search MP3 Tracks by Genre");
        io.print("(8) Search MP3 Tracks by Artist");
        io.print("(9) Search MP3 Tracks by Album");
        io.print("(10) Show Average Age of MP3 Tracks in the Collection");
        io.print("(11) Show Newest MP3 Track in the Collection");
        io.print("(12) Show Oldest MP3 Track in the Collection");
        io.print("(13) Show Average Number of Notes associated with Tracks in Collection");
        io.print("(14) Exit");
        
        try {
            selection = io.readInt("Please select from the above choices.", 1, 14);
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
        LocalDate ld = LocalDate.now();
        String title = "";
        String releaseDate = "";
        String album = "";
        String artistName = "";
        String genre = "";
        String moreInfo = "";
        
        //Ensuring that these fields are filled in and aren't left blank
        do {
            title = io.readString("Enter track title:");
        } while (title.equals(""));
        
        do {
            releaseDate = io.readString("Enter release date (yyyy-MM-dd):");
            ld = LocalDate.parse(releaseDate, DateTimeFormatter.ISO_DATE);
        } while (releaseDate.equals(""));
        
        do {
            album = io.readString("Enter album name:");
        } while (album.equals(""));
        
        do {
            artistName = io.readString("Enter track's artist:");
        } while (artistName.equals(""));
        
        do {
            genre = io.readString("Enter track's genre:");
        } while (genre.equals(""));
        
        //Make it optional to input extra notes
        //But make sure there's at least a space in moreInfo so that it's not blank
        moreInfo = io.readString("Enter any personal notes or ratings:");
        
        if (moreInfo.equals("")) {
            moreInfo = " ";
        }
                
        mp3 currentMP3 = new mp3(title);
        
        currentMP3.setReleaseDate(ld); //putting the LocalDate obj in
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
            io.readString("No MP3 track found! Please press Enter.");
        } 
    }
    
    //Ask user for info for new info to replace on existing mp3 track
    //Option 3: edit
    public mp3 editMP3(mp3 currentMP3) {
        int selection = 0;
        boolean keepGoing = true;
        
        //Create copy of the inputted mp3
        //You're going to return this copy called newMP3
        //Because the user may not actually edit everything
        mp3 newMP3 = new mp3(currentMP3.getTitle());
        newMP3.setReleaseDate(currentMP3.getReleaseDate());
        newMP3.setAlbum(currentMP3.getAlbum());
        newMP3.setArtistName(currentMP3.getArtistName());
        newMP3.setGenre(currentMP3.getGenre());
        newMP3.setMoreInfo(currentMP3.getMoreInfo());
        
        //Initialize the field variables in case the user wants to edit them
        LocalDate ld;
        String title = "";
        String releaseDate = "";
        String album = "";
        String artistName = "";
        String genre = "";
        String moreInfo = "";
        
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
                    //Ensure that these fields are not left blank
                    do {
                        title = io.readString("Enter new track title:");
                    } while (title.equals(""));
                    
                    newMP3.setTitle(title);
                    break;
                case 2:
                    do {
                        releaseDate = io.readString("Enter new release date (yyyy-MM-dd):");
                        ld = LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        //releaseDate = ld.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    } while (releaseDate.equals(""));
                    
                    newMP3.setReleaseDate(ld);
                    break;
                case 3:
                    do {
                        album = io.readString("Enter new album name:");
                    } while (album.equals(""));
                    
                    newMP3.setAlbum(album);
                    break;
                case 4:
                    do {
                        artistName = io.readString("Enter new track's artist:");
                    } while (artistName.equals(""));
                    
                    newMP3.setArtistName(artistName);
                    break;
                case 5:
                    do {
                        genre = io.readString("Enter new track genre:");
                    } while (genre.equals(""));
                    
                    newMP3.setGenre(genre);
                    break;
                case 6:
                    //Make it optional to input extra notes
                    //But make sure there's at least a space in moreInfo so that it's not blank
                    moreInfo = io.readString("Enter any personal notes or ratings:");
        
                    if (moreInfo.equals("")) {
                        moreInfo = " ";
                    }
                    
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
        } else {
            io.print("No MP3 track found! Please press Enter.");
        }
        
        io.readString("Press Enter to go back to the main screen.");    
    }
    
    //Option 6: Display all MP3 Tracks within last [ ] years
    public int getNumYearsInput() {
        int selection = 0;
        try {
                selection = io.readInt("\nEnter the number of years: ");
            } catch (InputMismatchException e) {
                io.print("Bad input!");
            }
        
        return selection;
    }
    
    //Option 7: Search MP3 Tracks by Genre
    public String getGenreInput() {
         return io.readString("Enter genre to search for: ");    
    }
    
    //Option 8: Search MP3 Tracks by Artist
    public String getArtistInput() {
         return io.readString("Enter artist to search for: ");
    }
    
    //Option 9: Search MP3 Tracks by Album
    public String getAlbumInput() {
         return io.readString("Enter genre to search for: ");  
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
