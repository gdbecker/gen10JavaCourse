package gdb.addressbook.dao;

import gdb.addressbook.dto.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @date Thursday December 12, 2019
 * @author garrettbecker
 */

public class AddressBookDaoFileImpl implements AddressBookDao {
    private Map<String, Address> addresses = new HashMap<>();
    public static final String FILE = "addressBook.txt";
    public static final String DELIMITER = "::";

    @Override
    public Address addAddress(String lastName, Address address) throws AddressBookPersistenceException {
        Address newAddress = addresses.put(lastName, address);
        return newAddress;
    }

    @Override
    public Address removeAddress(String lastName) throws AddressBookPersistenceException {
        Address removedAddress = addresses.remove(lastName);
        return removedAddress;
    }

    @Override
    public Address getAddress(String lastName) throws AddressBookPersistenceException {
        return addresses.get(lastName);
    }

    @Override
    public List<Address> getAllAddresses() throws AddressBookPersistenceException {
        return new ArrayList<Address>(addresses.values());
    }

    @Override
    public String getNumAddresses() throws AddressBookPersistenceException {
        int counter = addresses.size();
        return ("There are " + counter + " addresses in the book.");
    }
    
    @Override
    public Address editAddress(String lastName, Address address) throws AddressBookPersistenceException {
        Address editedAddress = addresses.put(lastName, address);
        return editedAddress;
    }
    
    private Address unmarshallAddress(String addressAsText){
        //addressAsText is expecting a line read in from our file.
        String[] addressTokens = addressAsText.split(DELIMITER);

        //Given the pattern above, the lastName is in index 1 of the array.
        String lastName = addressTokens[1];

        //Use the lastName to instantiate the new Address object
        Address addressFromFile = new Address(lastName);

        //Load the rest of the data from the file and fill in the Address object's fields
        //Index 0 - firstName
        addressFromFile.setFirstName(addressTokens[0]);

        //Index 2 - street
        addressFromFile.setStreet(addressTokens[2]);

        //Index 3 - city
        addressFromFile.setCity(addressTokens[3]);
    
        //Index 4 - state
        addressFromFile.setState(addressTokens[4]);
        
        //Index 5 - city
        addressFromFile.setZip(addressTokens[5]);

        //Return the new Address object
        return addressFromFile;
    }
    
    public void loadAddressBook() throws AddressBookPersistenceException {
        Scanner scanner;
        
        try {
            //Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(FILE)));
        } catch (FileNotFoundException e) {
            throw new AddressBookPersistenceException("-_- Could not load address book data into memory.", e);
        }
        
        //currentLine holds the most recent line read from the file
        String currentLine;
        
        //currentAddress holds the most recent address unmarshalled
        Address currentAddress;
        
        //Go through the FILE line by line, decoding each line into an 
        //Address object by calling the unmarshallAddress method.
        //Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            //Get the next line in the file
            currentLine = scanner.nextLine();
            
            //Unmarshall the line into an Address
            currentAddress = unmarshallAddress(currentLine);
            
            addresses.put(currentAddress.getLastName(), currentAddress);
        }
    }
    
    private String marshallAddress(Address anAddress){
        //We need to turn an Address object into a line of text for our file.

        //Start with the firstName, since that's supposed to be first.
        String addressAsText = anAddress.getFirstName() + DELIMITER;

        //add the rest of the properties in the correct order:

        //LastName
        addressAsText += anAddress.getLastName() + DELIMITER;

        //Street
        addressAsText += anAddress.getStreet() + DELIMITER;
    
        //City
        addressAsText += anAddress.getCity() + DELIMITER;
    
        //State
        addressAsText += anAddress.getState() + DELIMITER;

        //Zip Code. Don't need another DELIMITER after this part
        addressAsText += anAddress.getZip();
    
        //Return this line of text
        return addressAsText;
    }
    
    /**
    * Writes all addresses in the address book out to FILE.  See loadAddressBook
    * for file format.
    * 
    * @throws AddressBookDaoException if an error occurs writing to the file
    */
    public void writeAddressBook() throws AddressBookPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(FILE));
        } catch (IOException e) {
            throw new AddressBookPersistenceException("Could not save address data.", e);
        }

        // Write out the Address objects to the file
        String addressAsText;
        List<Address> addressList = this.getAllAddresses();
        for (Address currentAddress : addressList) {
            // turn an Address into a String
            addressAsText = marshallAddress(currentAddress);
            // write the Address object to the file
            out.println(addressAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
   
    /**
    * Reads all addresses in FILE to addresses map. 
    * 
    * @throws AddressBookDaoException if an error occurs while reading from
    * the file
    */
    public void loadAllAddressesFromFile() throws AddressBookPersistenceException {
        Scanner scanner;
        
        try {
            //Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(FILE)));
        } catch (FileNotFoundException e) {
            throw new AddressBookPersistenceException("-_- Could not load address book data into memory.", e);
        }
        
        //currentLine holds the most recent line read from the file
        String currentLine;
        
        //currentAddress holds the most recent address unmarshalled
        Address currentAddress;
        
        //Go through the FILE line by line, decoding each line into an 
        //Address object by calling the unmarshallAddress method.
        //Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            //Get the next line in the file
            currentLine = scanner.nextLine();
            
            //Unmarshall the line into an Address
            currentAddress = unmarshallAddress(currentLine);
            
            addresses.put(currentAddress.getLastName(), currentAddress);
        }
    }
}
