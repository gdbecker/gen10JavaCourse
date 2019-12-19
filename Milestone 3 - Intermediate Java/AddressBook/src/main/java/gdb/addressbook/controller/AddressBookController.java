package gdb.addressbook.controller;

import gdb.addressbook.dao.AddressBookDao;
import gdb.addressbook.dao.AddressBookPersistenceException;
import gdb.addressbook.dto.Address;
import gdb.addressbook.service.AddressBookDataValidationException;
import gdb.addressbook.service.AddressBookDuplicateLastNameException;
import gdb.addressbook.service.AddressBookServiceLayer;
import gdb.addressbook.service.AddressBookServiceLayerImpl;
import gdb.addressbook.ui.AddressBookView;
import java.util.List;

/**
 * @date Thursday December 12, 2019
 * @author garrettbecker
 */

public class AddressBookController {
    //Replace the dao below with the new Service Layer
    //AddressBookDao dao;
    
    private AddressBookServiceLayer service;
    AddressBookView view;
    
    public AddressBookController(AddressBookServiceLayer service, AddressBookView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        //Load the existing address book library into memory
        try {
            loadFile(); 
        } catch (AddressBookPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
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
        } catch (AddressBookPersistenceException e){
            view.displayErrorMessage(e.getMessage());
        }
        
        //Write the memory address book to the file
        try {
            writeFile(); 
        } catch (AddressBookPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        exitMessage();
     }
       
    private int getMenuSelection() {
        return view.showMenuGetSelection();
    }
    
    //Option 1
    private void createAddress() throws AddressBookPersistenceException {
        view.displayCreateAddressBanner();
        
        boolean hasErrors = false;
        do {
            Address newAddress = view.getNewAddress();
            try {
                service.createAddress(newAddress);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (AddressBookDuplicateLastNameException | AddressBookDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
        
    }
    
    //Option 2
    private void removeAddress() throws AddressBookPersistenceException {
        view.displayRemoveAddressBanner();
        String lastName = view.getLastNameChoice();
        service.removeAddress(lastName);
        view.displayRemoveSuccessBanner();
    }
    
    //Option 3
    private void viewAddress() throws AddressBookPersistenceException {
        view.displayDisplayAddressBanner();
        String lastName = view.getLastNameChoice();
        Address address = service.getAddress(lastName);
        view.displayAddress(address);
    }
    
    //Option 4
    private void viewNumAddresses() throws AddressBookPersistenceException {
        String s = service.getNumAddresses();
        view.printToScreen(s);
        view.returnToMainMenu();
    }
    
    //Option 5
    private void listAddresses() throws AddressBookPersistenceException {
        view.displayDisplayAllBanner();
        List<Address> addressList = service.getAllAddresses();
        view.displayAddressList(addressList);
    }
    
    //Option 6
    private void editAddress() throws AddressBookPersistenceException {
        view.displayEditAddressBanner();
        String lastName = view.getLastNameChoice(); //ask user for address to edit
        Address address = service.getAddress(lastName); //pull up that address
        view.displayAddressToEdit(address); //show original address, confirm to edit
        
        
        boolean hasErrors = false;
        do {
            Address editedAddress = view.editAddress(); //get new address details from user
            try {
                service.editAddress(editedAddress);
                view.displayEditSuccessBanner(); //confirm edits are made
                hasErrors = false;
            } catch (AddressBookDuplicateLastNameException | AddressBookDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }
    
    
    private void loadFile() throws AddressBookPersistenceException {
        service.loadAddressBook();
    }
    
    private void writeFile() throws AddressBookPersistenceException {
        service.writeAddressBook();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}
