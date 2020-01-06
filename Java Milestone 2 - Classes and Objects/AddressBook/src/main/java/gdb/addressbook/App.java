package gdb.addressbook;

import gdb.addressbook.controller.AddressBookController;
import gdb.addressbook.dao.AddressBookDaoFileImpl;
import gdb.addressbook.ui.AddressBookView;
import gdb.addressbook.ui.UserIO;
import gdb.addressbook.ui.UserIOConsoleImpl;

/**
 * @date Thursday December 12, 2019
 * @author garrettbecker
 */

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        AddressBookView myView = new AddressBookView(myIo);
        AddressBookDaoFileImpl myDao = new AddressBookDaoFileImpl();
        AddressBookController controller = new AddressBookController(myDao, myView);
        controller.run();
    }
    
}
