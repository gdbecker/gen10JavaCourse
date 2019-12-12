package gdb.classroster;

import gdb.classroster.controller.ClassRosterController;
import gdb.classroster.dao.ClassRosterDaoFileImpl;
import gdb.classroster.ui.ClassRosterView;
import gdb.classroster.ui.UserIO;
import gdb.classroster.ui.UserIOConsoleImpl;

/**
 * @date Thursday December 12, 2019
 * @author garrettbecker
 */

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);
        ClassRosterDaoFileImpl myDao = new ClassRosterDaoFileImpl();
        ClassRosterController controller = new ClassRosterController(myDao, myView);
        controller.run();
    }
}
