package gdb.vendingmachine;

import gdb.vendingmachine.controller.VendingMachineController;
import gdb.vendingmachine.dao.VendingMachineAuditDao;
import gdb.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import gdb.vendingmachine.dao.VendingMachineDao;
import gdb.vendingmachine.dao.VendingMachineDaoFileImpl;
import gdb.vendingmachine.service.VendingMachineService;
import gdb.vendingmachine.service.VendingMachineServiceImpl;
import gdb.vendingmachine.ui.UserIO;
import gdb.vendingmachine.ui.UserIOConsoleImpl;
import gdb.vendingmachine.ui.VendingMachineView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

public class App {
    public static void main(String[] args) {
        //Replacing the code below with Spring Dependency Injection
        //applicationContext.xml source file contains Spring Dependency connection
        
        //Instantiate the UserIO
        UserIO myIO = new UserIOConsoleImpl();
        
        //Instantiate the view
        VendingMachineView myView = new VendingMachineView(myIO);
        
        //Instantiate DAO objects
        VendingMachineDao mainDao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
        
        //Instantiate Service Layer object
        VendingMachineService myService = new VendingMachineServiceImpl(mainDao, auditDao);
        
        //Instantiate the Controller
        VendingMachineController myController = new VendingMachineController(myService, myView);
        
        //Run the program
        myController.run();
        
        /*
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = 
           ctx.getBean("controller", VendingMachineController.class);
        controller.run();
        */
        
    }
}
