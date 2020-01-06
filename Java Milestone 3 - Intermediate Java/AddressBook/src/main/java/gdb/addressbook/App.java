package gdb.addressbook;

import gdb.addressbook.controller.AddressBookController;
import gdb.addressbook.dao.AddressBookDaoFileImpl;
import gdb.addressbook.service.AddressBookServiceLayer;
import gdb.addressbook.service.AddressBookServiceLayerImpl;
import gdb.addressbook.ui.AddressBookView;
import gdb.addressbook.ui.UserIO;
import gdb.addressbook.ui.UserIOConsoleImpl;

/**
 * @date Thursday December 12, 2019
 * @author garrettbecker
 */

public class App {
    public static void main(String[] args) {
        //Instantiate the new UserIO object to collect user input
        UserIO myIo = new UserIOConsoleImpl();
        
        //Instantiate the view object to interact with the user. myIo is a component
        AddressBookView myView = new AddressBookView(myIo);
        
        //Instantiate the dao object for interacting with the data
        AddressBookDaoFileImpl myDao = new AddressBookDaoFileImpl();
        
        //Instantiate the Service Layer object. myDao is a component
        AddressBookServiceLayer myService = new AddressBookServiceLayerImpl(myDao);
        
        //Instantiate the new Controller object with myService and myView as components
        AddressBookController controller = new AddressBookController(myService, myView);
        
        //Run the application
        controller.run();
    }
    
}
