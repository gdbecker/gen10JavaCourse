package gdb.mp3library.controller;

import gdb.mp3library.dao.mp3libraryDao;
import gdb.mp3library.dao.mp3libraryDaoException;
import gdb.mp3library.dto.mp3;
import gdb.mp3library.ui.mp3libraryView;
import java.util.List;

/**
 * Milestone 2 Assessment
 * @author garrettbecker
 */

public class mp3libraryController {
    mp3libraryDao dao;
    mp3libraryView view;
    
    public mp3libraryController(mp3libraryDao dao, mp3libraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        int addRemoveEditMenuSelection = 0;
        
        //Load the existing MP3 library into memory
        try {
            loadLibrary(); 
        } catch (mp3libraryDaoException e) {
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
                        keepGoing = false;
                        break;
                    default:
                        //unknownCommand();
                        keepGoing = false;
                }
            
            }
        } catch (mp3libraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        //Write the MP3 library in memory back to the file
        try {
            writeLibrary(); 
        } catch (mp3libraryDaoException e) {
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
    private void addMP3() throws mp3libraryDaoException {
        view.displayAddMP3Banner();
        mp3 newMP3 = view.getNewMP3();
        dao.addMP3(newMP3.getTitle(), newMP3);
        view.displayAddMP3SuccessBanner();
    }
    
    //Option 2: remove
    private void removeMP3() throws mp3libraryDaoException {
        view.displayRemoveMP3Banner();
        String title = view.getTitleChoice();
        mp3 findMP3 = dao.displayMP3Info(title);
        
        if (findMP3 == null) {
            view.displayErrorBanner(); //if input from user doesn't match any tracks
        } else {
            dao.removeMP3(title);
            view.displayRemoveSuccessBanner();
        }
    }
    
    //Option 3: edit
    private void editMP3() throws mp3libraryDaoException {
        view.displayEditMP3Banner();
        String title = view.getTitleChoice(); //ask user for title of track to edit
        mp3 toEditMP3 = dao.displayMP3Info(title); //dao gets mp3 object with title
        view.showMP3ToEdit(toEditMP3); //shows that track's info to screen
        
        if (toEditMP3 == null) {
            view.displayErrorBanner(); //if input from user doesn't match any tracks
        } else {
            mp3 editedMP3 = view.editMP3(toEditMP3); //get new details for track from user
            dao.editMP3(editedMP3.getTitle(), editedMP3); //dao puts new track info into collection
            
            if (!title.equals(editedMP3.getTitle())) {
                dao.removeMP3(title); //dao removes old track info from memory, only if name changes
            }
            
            //view.displayEditSuccessBanner();
        }
        
    }
    
    //Option 4: display all tracks
    private void displayAllMP3() throws mp3libraryDaoException {
        view.displayAllMP3TracksBanner();
        List<mp3> mp3List = dao.showAllMP3();
        view.displayMP3List(mp3List);
    }
    
    //Option 5: display single track
    private void displaySingleMP3() throws mp3libraryDaoException {
        String title = view.getTitleChoice();
        mp3 thisMP3 = dao.displayMP3Info(title);
        view.displayMP3(thisMP3);
    }
    
    //Load from library file (before menu pops up)
    private void loadLibrary() throws mp3libraryDaoException {
        dao.loadMP3Library();
        view.displayLoadSuccessBanner();
    }
    
    //Write to library file (afer program completes)
    private void writeLibrary() throws mp3libraryDaoException {
        dao.writeMP3Library();
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
