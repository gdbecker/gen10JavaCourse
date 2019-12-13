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
        
        try {
            while (keepGoing) {
            
                menuSelection = getMenuSelection();
            
                switch (menuSelection) {
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
                        displayAllMP3();
                        break;
                    case 5:
                        displaySingleMP3();
                        break;
                    case 6:
                        loadLibrary();
                        break;
                    case 7:
                        writeLibrary();
                        break;
                    case 8:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            
            }
        } catch (mp3libraryDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
        
        exitMessage();
            
        }
    
    public int getMenuSelection() {
        return view.showMenuGetSelection();
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
        dao.removeMP3(title);
        view.displayRemoveSuccessBanner();
    }
    
    //Option 3: edit
    private void editMP3() throws mp3libraryDaoException {
        view.displayEditMP3Banner();
        String title = view.getTitleChoice(); //ask user for title of track to edit
        mp3 toEditMP3 = dao.displayMP3Info(title); //dao gets mp3 object with title
        view.showMP3ToEdit(toEditMP3); //shows that track's info to screen
        mp3 editedMP3 = view.editMP3(toEditMP3); //get new details for track from user
        dao.editMP3(title, editedMP3); //dao puts new track info into collection
        view.displayEditSuccessBanner();
    }
    
    //Option 4: display all tracks
    private void displayAllMP3() throws mp3libraryDaoException {
        view.displayAllMP3TracksBanner();
        List<mp3> mp3List = dao.showAllMP3();
        view.displayMP3List(mp3List);
    }
    
    //Option 5: display single track
    private void displaySingleMP3() throws mp3libraryDaoException {
        view.displaySingleMP3TrackBanner();
        String title = view.getTitleChoice();
        mp3 thisMP3 = dao.displayMP3Info(title);
        view.displayMP3(thisMP3);
    }
    
    //Option 6: load from library file
    private void loadLibrary() throws mp3libraryDaoException {
        view.displayLoadBanner();
        dao.loadMP3Library();
        view.displayLoadSuccessBanner();
    }
    
    //Option 7: write to library file
    private void writeLibrary() throws mp3libraryDaoException {
        view.displayWriteBanner();
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
