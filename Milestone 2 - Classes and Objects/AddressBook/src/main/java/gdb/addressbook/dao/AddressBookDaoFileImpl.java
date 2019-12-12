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
    public static final String ABOOK_FILE = "addressBook.txt";
    public static final String DELIMITER = "::";

    @Override
    public Address addAddress(String lastName, Address address) throws AddressBookDaoException {
        loadAddressBook();
        Address newAddress = addresses.put(lastName, address);
        writeAddressBook();
        return newAddress;
    }

    @Override
    public Address removeAddress(String lastName) throws AddressBookDaoException {
        loadAddressBook();
        Address removedAddress = addresses.remove(lastName);
        writeAddressBook();
        return removedAddress;
    }

    @Override
    public Address getAddress(String lastName) throws AddressBookDaoException {
        loadAddressBook();
        return addresses.get(lastName);
    }

    @Override
    public List<Address> getAllAddresses() throws AddressBookDaoException {
        loadAddressBook();
        return new ArrayList<Address>(addresses.values());
    }

    @Override
    public String getNumAddresses() throws AddressBookDaoException {
        loadAddressBook();
        int counter = addresses.size();
        return ("There are " + counter + " addresses in the book.");
    }
    
    private Address unmarshallAddress(String addressAsText){
        // addressAsText is expecting a line read in from our file.
        String[] addressTokens = addressAsText.split(DELIMITER);

        // Given the pattern above, the lastName is in index 1 of the array.
        String lastName = addressTokens[1];

        // Which we can then use to create a new Address object to satisfy
        // the requirements of the Address constructor.
        Address addressFromFile = new Address(lastName);

        // However, there are 3 remaining tokens that need to be set into the
        // new address object. Do this manually by using the appropriate setters.

        // Index 0 - firstName
        addressFromFile.setFirstName(addressTokens[0]);

        // Index 2 - street
        addressFromFile.setStreet(addressTokens[2]);

        // Index 3 - city
        addressFromFile.setCity(addressTokens[3]);
    
        // Index 4 - state
        addressFromFile.setState(addressTokens[3]);
        
        // Index 5 - city
        addressFromFile.setZip(addressTokens[3]);

        // We have now created a address! Return it!
        return addressFromFile;
    }
    
    private void loadAddressBook() throws AddressBookDaoException {
        Scanner scanner;
        
        try {
            //Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(ABOOK_FILE)));
        } catch (FileNotFoundException e) {
            throw new AddressBookDaoException("-_- Could not load address book data into memory.", e);
        }
        
        //currentLine holds the most recent line read from the file
        String currentLine;
        
        //currentAddress holds the most recent student unmarshalled
        Address currentAddress;
        
        //Go through the ABOOK_FILE line by line, decoding each line into a 
        //Address object by calling the unmarshallAddress method.
        //Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            //Get the next line in the file
            currentLine = scanner.nextLine();
            
            //Unmarshall the line into a Student
            currentAddress = unmarshallAddress(currentLine);
        }
    }
    
    private String marshallStudent(Address anAddress){
    // We need to turn an Address object into a line of text for our file.

    // It's not a complicated process. Just get out each property,
    // and concatenate with our DELIMITER as a kind of spacer. 

    // Start with the firstName, since that's supposed to be first.
    String addressAsText = anAddress.getFirstName() + DELIMITER;

    // add the rest of the properties in the correct order:

    // LastName
    addressAsText += anAddress.getLastName() + DELIMITER;

    // Street
    addressAsText += anAddress.getStreet() + DELIMITER;
    
    // City
    addressAsText += anAddress.getCity() + DELIMITER;
    
    // State
    addressAsText += anAddress.getState() + DELIMITER;

    // Zip Code - don't forget to skip the DELIMITER here.
    addressAsText += anAddress.getZip();

    // We have now turned a student to text! Return it!
    return addressAsText;
    }
    
    /**
    * Writes all addresses in the address book out to a ABOOK_FILE.  See loadAddressBook
    * for file format.
    * 
    * @throws AddressBookDaoException if an error occurs writing to the file
    */
    private void writeAddressBook() throws AddressBookDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ABOOK_FILE));
        } catch (IOException e) {
            throw new AddressBookDaoException("Could not save student data.", e);
        }

        // Write out the Student objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        String studentAsText;
        List<Address> addressList = this.getAllAddresses();
        for (Address currentAddress : addressList) {
            // turn a Student into a String
            studentAsText = marshallStudent(currentAddress);
            // write the Student object to the file
            out.println(studentAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
    
}
