package gdb.mp3library;

import gdb.mp3library.controller.mp3libraryController;
import gdb.mp3library.dao.mp3libraryDao;
import gdb.mp3library.dao.mp3libraryDaoFileImpl;
import gdb.mp3library.service.mp3libraryServiceLayer;
import gdb.mp3library.service.mp3libraryServiceLayerImpl;
import gdb.mp3library.ui.UserIO;
import gdb.mp3library.ui.UserIOConsoleImpl;
import gdb.mp3library.ui.mp3libraryView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Milestone 2 Assessment
 * @author garrettbecker
 */

public class App {
    public static void main(String[] args) {
        //Replacing the code below with Spring Dependency Injection
        //applicationContext.xml source file contains Spring Dependency connection
        /*
        //Instantiate the IO type
        UserIO io = new UserIOConsoleImpl();
        
        //Instantiate the view
        mp3libraryView myView = new mp3libraryView(io);
        
        //Instantiate the dao
        mp3libraryDao myDao = new mp3libraryDaoFileImpl();
        
        //Instantiate the service 
        mp3libraryServiceLayer myService = new mp3libraryServiceLayerImpl(myDao);
        
        //Instantiate the controller
        mp3libraryController controller = new mp3libraryController(myService, myView);
        
        //Start the app
        controller.run();
        */
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        mp3libraryController controller = ctx.getBean("controller", mp3libraryController.class);
        controller.run();
    }
}
