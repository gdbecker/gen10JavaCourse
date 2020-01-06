package gdb.classroster;

import gdb.classroster.controller.ClassRosterController;
import gdb.classroster.dao.ClassRosterAuditDao;
import gdb.classroster.dao.ClassRosterAuditDaoFileImpl;
import gdb.classroster.dao.ClassRosterDao;
import gdb.classroster.dao.ClassRosterDaoFileImpl;
import gdb.classroster.service.ClassRosterServiceLayer;
import gdb.classroster.service.ClassRosterServiceLayerImpl;
import gdb.classroster.ui.ClassRosterView;
import gdb.classroster.ui.UserIO;
import gdb.classroster.ui.UserIOConsoleImpl;

/**
 * @date Thursday December 12, 2019
 * Monday December 16, 2019
 * @author garrettbecker
 */

public class App {
    public static void main(String[] args) {
        //Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImpl();
        
        //Instantiate the View and wire the UserIO implementation into it
        ClassRosterView myView = new ClassRosterView(myIo);
        
        //Instantiate the DAO
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        
        //Instantiate the Audit DAO
        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
        
        //Instantiate the Service layer and wire in the DAO and Audit DAO into it
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
        
        //Instantiate the Controller and wire the Service layer into it
        ClassRosterController controller = new ClassRosterController(myService, myView);
        
        //Kick off the Controller
        controller.run();
    }
}
