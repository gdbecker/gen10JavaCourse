package gdb.addressbook.ui;

import gdb.addressbook.dto.Address;
import java.util.List;

/**
 * @date Thursday December 12, 2019
 * and Friday December 13, 2019
 * @author garrettbecker
 */

public class AddressBookView {
    private UserIO io;

    //Constructor for making this view
    public AddressBookView(UserIO io) {
        this.io = io;
    }
    
    public int showMenuGetSelection() {
        io.print("Please select the operation you wish to perform:");
        io.print("(1) Add Address");
        io.print("(2) Delete Address");
        io.print("(3) Find Address");
        io.print("(4) List Address Count");
        io.print("(5) List All Addresses");
        io.print("(6) Edit an Address");
        io.print("(7) Exit");
        
        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
    public Address getNewAddress() {
        String firstName = io.readString("Please enter First Name:");
        String lastName = io.readString("Please enter Last Name:");
        String street = io.readString("Please enter Street:");
        String city = io.readString("Please enter city:");
        String state = io.readString("Please enter State (##):");
        String zip = io.readString("Please enter Zip Code:");
        
        Address currentAddress = new Address(lastName);
        
        currentAddress.setFirstName(firstName);
        currentAddress.setStreet(street);
        currentAddress.setCity(city);
        currentAddress.setState(state);
        currentAddress.setZip(zip);
        
        return currentAddress;
    }
    
    public void displayCreateAddressBanner() {
        io.print("** Create New Address **");
    }
    
    public void displayCreateSuccessBanner() {
        io.readInt("New Address successfully created. Press 1 to go to the main menu.", 1, 1);
    }
    
    public void displayRemoveAddressBanner() {
        io.print("** Delete Address **");
    }
    
    public void displayRemoveSuccessBanner() {
        io.readInt("Address successfully deleted. Press 1 to go to the main menu.", 1, 1);
    }
    
    public void displayAddressList(List<Address> addressList) {
        for (Address currentAddress : addressList) {
            io.print(currentAddress.getFirstName() + " " + currentAddress.getLastName());
            io.print(currentAddress.getStreet());
            io.print(currentAddress.getCity() + ", " + currentAddress.getState() + " " + currentAddress.getZip());
        }
        
        io.readInt("Press 1 to go to the main menu.", 1, 1);
    }
    
    public void displayDisplayAllBanner() {
        io.print("** Display All Addresses **");
    }
    
    public void displayDisplayAddressBanner() {
        io.print("** Display Address **");
    }
    
    public void displayAddress(Address address) {
        if (address != null) {
            io.print(address.getFirstName() + " " + address.getLastName());
            io.print(address.getStreet());
            io.print(address.getCity() + ", " + address.getState() + " " + address.getZip());
            io.print("");
        } else {
            io.print("No such address found.");
        }
        
        io.readInt("Press 1 to go to the main menu.", 1, 1);
    }
    
    public String getLastNameChoice() {
        return io.readString("Please enter Last Name associated with address:");
    }
    
    public void displayEditAddressBanner() {
        io.print("** Edit Address **");
    }
    
    public void displayEditSuccessBanner() {
        io.readInt("Address successfully edited. Press 1 to go to the main menu.", 1, 1);
    }
    
    public void displayAddressToEdit(Address address) {
        if (address != null) {
            io.print(address.getFirstName() + " " + address.getLastName());
            io.print(address.getStreet());
            io.print(address.getCity() + ", " + address.getState() + " " + address.getZip());
            io.print("");
        } else {
            io.print("No such address found.");
        }
        
        io.readString("Press enter to go to edit this address.");
    }
    
    public Address editAddress() {
        String firstName = io.readString("Please enter First Name:");
        String lastName = io.readString("Please enter Last Name:");
        String street = io.readString("Please enter Street:");
        String city = io.readString("Please enter city:");
        String state = io.readString("Please enter State (##):");
        String zip = io.readString("Please enter Zip Code:");
        
        Address editedAddress = new Address(lastName);
        
        editedAddress.setFirstName(firstName);
        editedAddress.setStreet(street);
        editedAddress.setCity(city);
        editedAddress.setState(state);
        editedAddress.setZip(zip);
        
        return editedAddress;
    }
    
    public void printToScreen(String s) {
        io.print(s);
    }
    
    public void returnToMainMenu() {
        io.readInt("Press 1 to go to the main menu.", 1, 1);
    }
    
    public void displayExitBanner() {
        io.print("Good bye!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
}
