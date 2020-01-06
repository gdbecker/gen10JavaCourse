package gdb.classroster;

import gdb.classroster.controller.ClassRosterController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @date Thursday December 12, 2019
 * Monday December 16, 2019
 * @author garrettbecker
 */

public class App {
    public static void main(String[] args) {
        //Replacing the code below with Spring Dependency Injection
        //applicationContext.xml source file contains Spring Dependency connection
        /*
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
        */
        
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller = 
           ctx.getBean("controller", ClassRosterController.class);
        controller.run();
        
    }
}
