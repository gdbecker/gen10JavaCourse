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

/**
 * Milestone 3 Summative
 * @author garrettbecker
 */

public class App {
    public static void main(String[] args) {
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
    }
}
