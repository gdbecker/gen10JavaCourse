package gdb.mp3library;

import gdb.mp3library.controller.mp3libraryController;
import gdb.mp3library.dao.mp3libraryDaoFileImpl;
import gdb.mp3library.ui.UserIO;
import gdb.mp3library.ui.UserIOConsoleImpl;
import gdb.mp3library.ui.mp3libraryView;

/**
 * Milestone 2 Assessment
 * @author garrettbecker
 */

public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        mp3libraryDaoFileImpl dao = new mp3libraryDaoFileImpl();
        mp3libraryView view = new mp3libraryView(io);
        mp3libraryController controller = new mp3libraryController(dao, view);
        controller.run();
    }
}
