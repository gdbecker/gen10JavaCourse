package gdb.addressbook.controller;

import gdb.addressbook.dao.AddressBookDao;
import gdb.addressbook.dao.AddressBookDaoException;
import gdb.addressbook.dto.Address;
import gdb.addressbook.ui.AddressBookView;
import java.util.List;

/**
 * @date Thursday December 12, 2019
 * @author garrettbecker
 */

public class AddressBookController {
    AddressBookDao dao;
    AddressBookView view;
    
    public AddressBookController(AddressBookDao dao, AddressBookView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        //loadFile();
        
        try {
            while (keepGoing) {
            
                menuSelection = getMenuSelection();
            
                switch (menuSelection) {
                    case 1:
                        createAddress();
                        break;
                    case 2:
                        removeAddress();
                        break;
                    case 3:
                        viewAddress();
                        break;
                    case 4:
                        viewNumAddresses();
                        break;
                    case 5:
                        listAddresses();
                        break;
                    case 6:
                        editAddress();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            
            }
        } catch (AddressBookDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
        
        exitMessage();
     }
       
    private int getMenuSelection() {
        return view.showMenuGetSelection();
    }
    
    //Option 1
    private void createAddress() throws AddressBookDaoException {
        view.displayCreateAddressBanner();
        Address newAddress = view.getNewAddress();
        dao.addAddress(newAddress.getLastName(), newAddress);
        view.displayCreateSuccessBanner();
    }
    
    //Option 2
    private void removeAddress() throws AddressBookDaoException {
        view.displayRemoveAddressBanner();
        String lastName = view.getLastNameChoice();
        dao.removeAddress(lastName);
        view.displayRemoveSuccessBanner();
    }
    
    //Option 3
    private void viewAddress() throws AddressBookDaoException {
        view.displayDisplayAddressBanner();
        String lastName = view.getLastNameChoice();
        Address address = dao.getAddress(lastName);
        view.displayAddress(address);
    }
    
    //Option 4
    private void viewNumAddresses() throws AddressBookDaoException {
        String s = dao.getNumAddresses();
        view.printToScreen(s);
        view.returnToMainMenu();
    }
    
    //Option 5
    private void listAddresses() throws AddressBookDaoException {
        view.displayDisplayAllBanner();
        List<Address> addressList = dao.getAllAddresses();
        view.displayAddressList(addressList);
    }
    
    //Option 6
    private void editAddress() throws AddressBookDaoException {
        view.displayEditAddressBanner();
        String lastName = view.getLastNameChoice(); //ask user for address to edit
        Address address = dao.getAddress(lastName); //pull up that address
        view.displayAddressToEdit(address); //show original address, confirm to edit
        Address editedAddress = view.editAddress(); //get new address details from user
        dao.editAddress(lastName, editedAddress); //write new info to the file
        view.displayEditSuccessBanner(); //confirm edits are made
    }
    
    /*
    private void loadFile() {
        dao.loadAllAddressesFromFile();
    }
    */
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}
