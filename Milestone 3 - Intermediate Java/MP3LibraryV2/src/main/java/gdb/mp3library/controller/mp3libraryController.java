package gdb.mp3library.controller;

import gdb.mp3library.dao.mp3libraryDao;
import gdb.mp3library.dao.mp3libraryPersistenceException;
import gdb.mp3library.dto.mp3;
import gdb.mp3library.service.mp3libraryDataValidationException;
import gdb.mp3library.service.mp3libraryDuplicateTitleException;
import gdb.mp3library.service.mp3libraryServiceLayer;
import gdb.mp3library.ui.mp3libraryView;
import java.util.List;

/**
 * Milestone 2 Assessment
 * @author garrettbecker
 */

public class mp3libraryController {
    //Replacing dao with service as a part of the constructor
    //mp3libraryDao dao;
    
    mp3libraryServiceLayer service;
    mp3libraryView view;
    
    public mp3libraryController(mp3libraryServiceLayer service, mp3libraryView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        int addRemoveEditMenuSelection = 0;
        
        //Load the existing MP3 library into memory
        try {
            loadLibrary(); 
        } catch (mp3libraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        view.displayWelcomeBanner();
        
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();
            
                switch (menuSelection) {
                    case 1:
                        addMP3();
                        
                        //Make it possible to keep adding, removing, or editing tracks
                        //in one sitting
                        do {
                            addRemoveEditMenuSelection = getAddEditRemoveMenuSelection();
                            
                            switch (addRemoveEditMenuSelection) {
                                case 1:
                                    addMP3();
                                    break;
                                case 2: 
                                    removeMP3();
                                    break;
                                case 3:
                                    editMP3();
                                    break;
                                case 4: 
                                    break;
                                default:
                                    addRemoveEditMenuSelection = 4;
                                    //unknownCommand();
                            }
                        } while (addRemoveEditMenuSelection != 4);
                        break;
                    case 2:
                        removeMP3();
                        
                        //Make it possible to keep adding, removing, or editing tracks
                        //in one sitting
                        do {
                            addRemoveEditMenuSelection = getAddEditRemoveMenuSelection();
                            
                            switch (addRemoveEditMenuSelection) {
                                case 1:
                                    addMP3();
                                    break;
                                case 2: 
                                    removeMP3();
                                    break;
                                case 3:
                                    editMP3();
                                    break;
                                case 4: 
                                    break;
                                default:
                                    addRemoveEditMenuSelection = 4;
                                    //unknownCommand();
                            }
                        } while (addRemoveEditMenuSelection != 4);
                        
                        break;
                    case 3:
                        editMP3();
                        
                        //Make it possible to keep adding, removing, or editing tracks
                        //in one sitting
                        do {
                            addRemoveEditMenuSelection = getAddEditRemoveMenuSelection();
                            
                            switch (addRemoveEditMenuSelection) {
                                case 1:
                                    addMP3();
                                    break;
                                case 2: 
                                    removeMP3();
                                    break;
                                case 3:
                                    editMP3();
                                    break;
                                case 4: 
                                    break;
                                default:
                                    addRemoveEditMenuSelection = 4;
                                    //unknownCommand();
                            }
                        } while (addRemoveEditMenuSelection != 4);
                        
                        break;
                    case 4:
                        displayAllMP3();
                        break;
                    case 5:
                        displaySingleMP3();
                        break;
                    case 6:
                        displayAllMP3WithinNYears();
                        break;
                    case 7:
                        //displaySingleMP3();
                        break;
                    case 8:
                        //displaySingleMP3();
                        break;
                    case 9:
                        //displaySingleMP3();
                        break;
                    case 10:
                        //displaySingleMP3();
                        break;
                    case 11:
                        //displaySingleMP3();
                        break;
                    case 12:
                        //displaySingleMP3();
                        break;
                    case 13:
                        //displaySingleMP3();
                        break;
                    case 14:
                        keepGoing = false;
                        break;
                    default:
                        //unknownCommand();
                        keepGoing = false;
                }
            
            }
        } catch (mp3libraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        //Write the MP3 library in memory back to the file
        try {
            writeLibrary(); 
        } catch (mp3libraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        exitMessage();
            
        }
    
    //Collect initial menu selection
    public int getMenuSelection() {
        return view.showMenuGetSelection();
    }
    
    //Allow user to add, remove, or edit more than one track in one session
    public int getAddEditRemoveMenuSelection() {
        return view.showAddRemoveEditMenuGetSelection();
    }
    
    //Option 1: add
    private void addMP3() throws mp3libraryPersistenceException {
        view.displayAddMP3Banner();
        
        boolean hasErrors = false;
        do {
            mp3 newMP3 = view.getNewMP3();
            try {
                service.addMP3(newMP3);
                view.displayAddMP3SuccessBanner();
                hasErrors = false;
            } catch (mp3libraryDuplicateTitleException | mp3libraryDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }
    
    //Option 2: remove
    private void removeMP3() throws mp3libraryPersistenceException {
        view.displayRemoveMP3Banner();
        String title = view.getTitleChoice();
        mp3 findMP3 = service.displayMP3Info(title);
        
        if (findMP3 == null) {
            view.displayErrorBanner(); //if input from user doesn't match any tracks
        } else {
            service.removeMP3(title);
            view.displayRemoveSuccessBanner();
        }
    }
    
    //Option 3: edit
    private void editMP3() throws mp3libraryPersistenceException {
        view.displayEditMP3Banner();
        String title = view.getTitleChoice(); //ask user for title of track to edit
        mp3 toEditMP3 = service.displayMP3Info(title); //dao gets mp3 object with title
        view.showMP3ToEdit(toEditMP3); //shows that track's info to screen
        
        
        boolean hasErrors = false;
        do {
            if (toEditMP3 == null) {
            
            } else {
                mp3 editedMP3 = view.editMP3(toEditMP3); //get new details for track from user
                try {
                    service.editMP3(editedMP3); //dao puts new track info into collection
                    
                    if (!title.equals(editedMP3.getTitle())) {
                        service.removeMP3(title); //dao removes old track info from memory, only if name changes
                    }
                    
                    hasErrors = false;
                    
                } catch (mp3libraryDuplicateTitleException | mp3libraryDataValidationException e) {
                    hasErrors = true;
                    view.displayErrorMessage(e.getMessage());
                }
            }
        } while (hasErrors);
    }
    
    //Option 4: display all tracks
    private void displayAllMP3() throws mp3libraryPersistenceException {
        view.displayAllMP3TracksBanner();
        List<mp3> mp3List = service.showAllMP3();
        view.displayMP3List(mp3List);
    }
    
    //Option 5: display single track
    private void displaySingleMP3() throws mp3libraryPersistenceException {
        String title = view.getTitleChoice();
        mp3 thisMP3 = service.displayMP3Info(title);
        view.displayMP3(thisMP3);
    }
    
    //Option 6: Display all MP3 Tracks within last [ ] years
    private void displayAllMP3WithinNYears() {
        int n = view.getNumYearsInput();
        List<mp3> mp3List = service.getAllMP3WithinLastNYears(n);
        view.displayMP3List(mp3List);
    }
    
    //Option 7: Search MP3 Tracks by Genre
    
    //Option 8: Search MP3 Tracks by Artist
    
    //Option 9: Search MP3 Tracks by Album
    
    //Option 10: Show Average Age of MP3 Tracks in the Collection
    
    //Option 11: Show Newest MP3 Track in the Collection
    
    //Option 12: Show Oldest MP3 Track in the Collection
    
    //Option 13: Show Average Number of Notes associated with Tracks in Collection
    
    //Load from library file (before menu pops up)
    private void loadLibrary() throws mp3libraryPersistenceException {
        service.loadMP3Library();
        view.displayLoadSuccessBanner();
    }
    
    //Write to library file (afer program completes)
    private void writeLibrary() throws mp3libraryPersistenceException {
        service.writeMP3Library();
        view.displayWriteSuccessBanner();
    }
    
    //Other messages to print if needed
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}
