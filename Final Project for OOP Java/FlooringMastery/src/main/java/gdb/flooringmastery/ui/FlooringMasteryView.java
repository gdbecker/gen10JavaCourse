package gdb.flooringmastery.ui;

import gdb.flooringmastery.dto.Order;
import gdb.flooringmastery.dto.Product;
import gdb.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Final Project OOP
 * @author garrettbecker
 */

public class FlooringMasteryView {
    private UserIO io = new UserIOConsoleImpl();
    
    //Constructor
    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }
    
    public int displayModeMenuGetSelection() {
        io.print("--- Program Mode Options ---");
        io.print("(1) Production");
        io.print("(2) Training");
        io.print("(3) Exit Program");
        io.print("");
        
        //Working variables
        int selection = 0;
        boolean validInput = false;
        
        //Catch for bad input but don't kick user out of program if they do
        //Keep asking for legit input
        try {
            selection = io.readInt("Choose program mode from number options above.", 1, 3);
            validInput = true;
        } catch (InputMismatchException e) {
            io.print("Bad input! Please enter a valid number from the above menu (1-3).");
        }
        
        while (!validInput) {
            try {
                String string = io.readString("");
                selection = io.readInt("Choose program mode from number options above or exit (1-3).", 1, 3);
                validInput = true;
            } catch (InputMismatchException e) {
                io.print("Bad input! Please try again.");
            }
        }
        
        return selection;
    }
    
    public int displayMainMenuGetSelection(int mode) {
        String modeLabel = "";
        if (mode == 1) {
            modeLabel = "PRODUCTION MODE";
        } else if (mode == 2) {
            modeLabel = "TRAINING MODE";
        }
        
        //Working variables
        int selection = 0;
        boolean validInput = false;
        
        io.print("");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("* --- " + modeLabel + " ---");
        io.print("* <<Flooring Program>>");
        io.print("* 1. Show all Orders");
        io.print("* 2. Search for Orders by Date");
        io.print("* 3. Add an Order");
        io.print("* 4. Edit an Order");
        io.print("* 5. Remove an Order");
        io.print("* 6. Save Current Work");
        io.print("* 7. Exit");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("");
         
        //Catch for bad input but don't kick user out of program if they do
        //Keep asking for legit input
        try {
            selection = io.readInt("Please enter a number option from the menu above (1-7).", 1, 7);
            validInput = true;
        } catch (InputMismatchException e) {
            io.print("Bad input! Please try again.");
        }
        
        while (!validInput) {
            try {
                String string = io.readString("");
                selection = io.readInt("Please enter a number option from the menu above.", 1, 7);
                validInput = true;
            } catch (InputMismatchException e) {
                io.print("Bad input! Please enter a valid number from the above menu (1-7).");
            }
        }
        
        return selection; 
    }
    
    //Option 2: Display orders by date input
    public LocalDate getOrderDateInputOption2() {        
        io.print("*** Display Orders ***");
        String orderDateString = null;
        LocalDate orderDateLD = LocalDate.now();
        boolean dateValid = false;
        
        do {
            io.print("");
            try {
                orderDateString = io.readString("Enter date for orders you wish to view (yyyy-MM-dd): ");
                orderDateLD = LocalDate.parse(orderDateString, DateTimeFormatter.ISO_DATE);
                dateValid = true;
            } catch (DateTimeParseException e) {
                io.print("Bad input! Try again.");
            }
        } while (!dateValid);
        
        return orderDateLD;
    }
    
    //Also used for Option 1: Display all orders
    public void displayOrders(List<Order> orderList) {
        io.print("");
        
        if (orderList.size() == 0) {
            io.print("No orders found! Returning to the main menu.");
            io.print("");
        } else {
            io.print("*** Displaying all Orders ***");
            for (Order currentOrder : orderList) {
                io.print("Order Number: " + currentOrder.getOrderNumber());
                io.print("Customer Name: " + currentOrder.getCustomerName());
                io.print("Order Date: " + currentOrder.getOrderDate());
                io.print("State: " + currentOrder.getState());
                io.print("Tax Rate: $" + currentOrder.getTaxRate());
                io.print("Product Type: " + currentOrder.getProductType());
                io.print("Area: " + currentOrder.getArea() + " sq ft");
                io.print("Cost per Square Foot: $" + currentOrder.getCostPerSquareFoot() + "/sq ft");
                io.print("Labor Cost per Square Foot: $" + currentOrder.getLaborCostPerSquareFoot() + "/sq ft");
                io.print("Material Cost: $" + currentOrder.getMaterialCost());
                io.print("Labor Cost: $" + currentOrder.getLaborCost());
                io.print("Tax: $" + currentOrder.getTax());
                io.print("Total Cost: $" + currentOrder.getTotal());
                io.print("");
            }
            io.readString("Press Enter to go back to the main screen.");
        }
    }
    
    //Option 3: Add an Order
    public Order addOrder(int highestOrderNum, int removedOrderNumWasHighest, List<Product> productList, List<Tax> taxList) {
        //Make sure that the next order number is not the one that was just removed
        //(if the one removed was once the highest in the system)
        int orderNumber = highestOrderNum + 1;
        if (orderNumber == removedOrderNumWasHighest) {
            orderNumber++;
        }
        
        String customerName = null;
        String orderDateString = null;
        LocalDate todayDate = LocalDate.now();
        LocalDate orderDateLD = LocalDate.now();
        boolean dateValid = false;
        String state = null;
        String productType = null;
        String areaAsString = null;
        double areaAsDouble = 0;
        boolean areaValid = false;
        String confirm = "";
        
        io.print("");
        io.print("*** Add an Order ***");
     
        //do-while loops ensure that these fields are filled in and aren't left blank
        //Get customer name
        do {
            io.print("");
            customerName = io.readString("Enter customer name:");
        } while (customerName.equals(""));
        
        //Get order date
        do {
            io.print("");
            try {
                orderDateString = io.readString("Enter order date either today or in the future (yyyy-MM-dd):");
                orderDateLD = LocalDate.parse(orderDateString, DateTimeFormatter.ISO_DATE);
                
                //Make sure that date input is either today or in the future
                Period timeDiff = Period.between(todayDate, orderDateLD);
                int dayTimeDiff = timeDiff.getDays();
                
                if (dayTimeDiff >= 0) {
                    dateValid = true;
                } else {
                    io.print("Bad input! Invalid date - must be either today or in the future.");
                }
                
            } catch (DateTimeParseException e) {
                io.print("Bad input! Try again.");
            }
        } while (!dateValid);
        
        //Get state of order (for tax purposes)
        do {
            io.print("");
            io.print("Options for US states:");
            
            //Print out each type of product so the user can see what the options are
            for (Tax t : taxList) {
                io.print(t.getState());
            }
            
            state = io.readString("Enter US state of order location (XX):");
        } while (state.equals("") || state.length() != 2 || (!state.equalsIgnoreCase("OH") && !state.equalsIgnoreCase("PA") && !state.equalsIgnoreCase("MI") && !state.equalsIgnoreCase("IN")));
        
        //Get material type for product
        do {
            io.print("");
            io.print("Options for product materials:");
            
            //Print out each type of product so the user can see what the options are
            for (Product p : productList) {
                io.print(p.getProductType());
            }
            
            productType = io.readString("Enter material type:");
        } while (productType.equals("") || (!productType.equalsIgnoreCase("Carpet") && !productType.equalsIgnoreCase("Laminate") && !productType.equalsIgnoreCase("Tile") && !productType.equalsIgnoreCase("Wood")));
        
        //Get product area
        do {
            io.print("");
            try {
                areaAsString = io.readString("Enter product area in sq ft:");
                areaAsDouble = Double.valueOf(areaAsString);
                areaValid = true;
                
                if (areaAsDouble < 0) {
                    io.print("Bad input! Try again.");
                }
            } catch (NumberFormatException e) {
                io.print("Bad input! Try again.");
            }
        } while (!areaValid || areaAsDouble < 0);
         
        //Confirm with user to make commitment to add order
        //Show what the user put in and have user input y/n/yes/no
        io.print("");
        io.print("New Order Details You Gave:");
        io.print("Customer name: " + customerName);
        io.print("Order Date: " + orderDateLD);
        io.print("State Location: " + state);
        io.print("Product Material: " + productType);
        io.print("Area: " + areaAsDouble + " sq ft");
        
        do {
            io.print("");
            confirm = io.readString("Commit to add order? (y/n):");
        } while (confirm.equals("") || (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n") && !confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("no")));
        
        Order currentOrder;
        
        if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
            //Make the new order with all fields put in
            currentOrder = new Order(orderNumber);
        
            currentOrder.setCustomerName(customerName);
            currentOrder.setOrderDate(orderDateLD);
            currentOrder.setState(state);
            currentOrder.setProductType(productType);
            currentOrder.setArea(areaAsDouble);
        } else {
            //Create an empty version of currentOrder to return
            //if "n" or "no" was inputted
            currentOrder = new Order(-1);
        }
        
        return currentOrder;
    }
    
    //Option 4: Edit an order
    public LocalDate getOrderDateInputOption4() {
        io.print("*** Edit an Order ***");
        String orderDateString = null;
        LocalDate orderDateLD = LocalDate.now();
        boolean dateValid = false;
        
        do {
            io.print("");
            try {
                orderDateString = io.readString("Enter date for the order you would like to edit (yyyy-MM-dd): ");
                orderDateLD = LocalDate.parse(orderDateString, DateTimeFormatter.ISO_DATE);
                dateValid = true;
            } catch (DateTimeParseException e) {
                io.print("Bad input! Try again.");
            }
        } while (!dateValid);
        
        return orderDateLD;
    }
    
    public String getCustomerNameInputOption4() {
        //Ensure that input field is not left blank
        
        String customerName = null;
        
        do {
            io.print("");
            customerName = io.readString("Enter customer name for order you would like to edit: "); 
        } while (customerName.equals(""));
        
        return customerName;
        
    }
    
    public Order editOrder(Order orderToEdit, List<Product> productList, List<Tax> taxList) {
        //Make duplicate of orderToEdit
        //This is what the method will return, but will make changes to the values
        //if the user inputs new data
        Order editedOrder = new Order(orderToEdit.getOrderNumber());
        editedOrder.setCustomerName(orderToEdit.getCustomerName());
        editedOrder.setOrderDate(orderToEdit.getOrderDate());
        editedOrder.setState(orderToEdit.getState());
        editedOrder.setProductType(orderToEdit.getProductType());
        editedOrder.setArea(orderToEdit.getArea());
        
        //Working variables
        String customerName = "";
        String orderDateString = "";
        LocalDate todayDate = LocalDate.now();
        LocalDate orderDateLD = LocalDate.now();
        boolean dateValid = false;
        String state = "";
        String productType = "";
        String areaAsString = null;
        double areaAsDouble = 0;
        boolean areaValid = false;
        
        io.print("");
        io.print("** Instructions:");
        io.print("** Press Enter to keep existing data or enter in new values.");
        
        //Customer Name
        io.print("");
        customerName = io.readString("Enter customer name (" + orderToEdit.getCustomerName() + "): ");
        if (!customerName.equals("")) {
            editedOrder.setCustomerName(customerName);
        }
        
        //Order Date
        io.print("");
        orderDateString = io.readString("Enter order date (" + orderToEdit.getOrderDate() + "): ");
        if (!orderDateString.equals("")) {
            //Catch for bad input for the date (if user edits it)
            do {
                try {
                    orderDateLD = LocalDate.parse(orderDateString, DateTimeFormatter.ISO_DATE);
                    editedOrder.setOrderDate(orderDateLD);
                    
                    //Make sure that date input is either today or in the future
                    Period timeDiff = Period.between(todayDate, orderDateLD);
                    int dayTimeDiff = timeDiff.getDays();
                
                    if (dayTimeDiff >= 0) {
                        dateValid = true;
                    } else {
                        io.print("Bad input! Invalid date - must be either today or in the future.");
                    }
                    
                } catch (DateTimeParseException e) {
                    io.print("Bad input! Try again.");
                    io.print("");
                    orderDateString = io.readString("Enter order date (" + orderToEdit.getOrderDate() + "): ");
                    if (orderDateString.equals("")) {
                        break;
                    }
                }
            } while(!dateValid);
        }
        
        //US State (for tax purposes)
        io.print("");
        io.print("Options for US states:");
        for (Tax t : taxList) {
            io.print(t.getState());
        }
        state = io.readString("Enter state location (" + orderToEdit.getState() + "): ");
        if (!state.equals("")) {
            //Catch for bad input for the state (if user edits it)
            while(state.length() != 2 || (!state.equalsIgnoreCase("OH") && !state.equalsIgnoreCase("PA") && !state.equalsIgnoreCase("MI") && !state.equalsIgnoreCase("IN"))) {
                state = io.readString("Enter state location (" + orderToEdit.getState() + "): ");
            }
            editedOrder.setState(state);
        }
        
        //Product Material Type
        io.print("");
        io.print("Options for product materials:");
        for (Product p : productList) {
            io.print(p.getProductType());
        }
        productType = io.readString("Enter material type (" + orderToEdit.getProductType() + "): ");
        if (!productType.equals("")) {
            //Catch for bad input for the product type (if user edits it)
            while ((!productType.equalsIgnoreCase("Carpet") && !productType.equalsIgnoreCase("Laminate") && !productType.equalsIgnoreCase("Tile") && !productType.equalsIgnoreCase("Wood"))) {
                productType = io.readString("Enter material type (" + orderToEdit.getProductType() + "): ");
            }
            editedOrder.setProductType(productType);
        }
        
        //Area of product
        do {
            io.print("");
            try {
                areaAsString = io.readString("Enter product area in sq ft (" + orderToEdit.getArea() + "): ");
                
                if (areaAsString.equals("")) {
                    break;
                }
                
                areaAsDouble = Double.valueOf(areaAsString);
                areaValid = true;
                
                if (areaAsDouble < 0) {
                    io.print("Bad input! Try again.");
                }
            } catch (NumberFormatException e) {
                io.print("Bad input! Try again.");
            }
        } while (!areaValid || areaAsDouble < 0);
        
        io.print("");
        io.print("*** Order successfully edited.");
        
        return editedOrder;
    }
    
    //Option 5: Remove an order
    public LocalDate getOrderDateInputOption5() {
        io.print("*** Remove an Order ***");
        String orderDateString = null;
        LocalDate orderDateLD = LocalDate.now();
        boolean dateValid = false;
        
        do {
            io.print("");
            try {
                orderDateString = io.readString("Enter date for the order you would like to remove (yyyy-MM-dd): ");
                orderDateLD = LocalDate.parse(orderDateString, DateTimeFormatter.ISO_DATE);
                dateValid = true;
            } catch (DateTimeParseException e) {
                io.print("Bad input! Try again.");
            }
        } while (!dateValid);
        
        return orderDateLD;
    }
    
    public String getCustomerNameInputOption5() {
        //Ensure that input field is not left blank
        
        String customerName = null;
        
        do {
            io.print("");
            customerName = io.readString("Enter customer name for order you would like to remove: "); 
        } while (customerName.equals(""));
        
        return customerName;
    }
    
    public String removeOrder(Order orderToRemove) {
        String confirm = "";
        
        //Confirm with user to remove the order
        //Show order details and have user input y/n/yes/no
        io.print("");
        io.print("Order details below ---");
        io.print("Order Number: " + orderToRemove.getOrderNumber());
        io.print("Customer Name: " + orderToRemove.getCustomerName());
        io.print("Order Date: " + orderToRemove.getOrderDate());
        io.print("State: " + orderToRemove.getState());
        io.print("Tax Rate: $" + orderToRemove.getTaxRate());
        io.print("Product Type: " + orderToRemove.getProductType());
        io.print("Area: " + orderToRemove.getArea() + " sq ft");
        io.print("Cost per Square Foot: $" + orderToRemove.getCostPerSquareFoot() + "/sq ft");
        io.print("Labor Cost per Square Foot: $" + orderToRemove.getLaborCostPerSquareFoot() + "/sq ft");
        io.print("Material Cost: $" + orderToRemove.getMaterialCost());
        io.print("Labor Cost: $" + orderToRemove.getLaborCost());
        io.print("Tax: $" + orderToRemove.getTax());
        io.print("Total Cost: $" + orderToRemove.getTotal());
        
        do {
            io.print("");
            confirm = io.readString("Commit to remove order? (y/n):");
        } while (confirm.equals("") || (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n") && !confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("no")));
        
        return confirm;
    }
    
    public void savedCurrentWorkMessage(int mode) {
        if (mode == 1) {
            io.print("");
            io.print("Data successfully saved to the file.");
            io.print("");
        } else if (mode == 2) {
            io.print("");
            io.print("In Production Mode, data will be saved to file. In Training Mode, data is not saved.");
            io.print("");
        }
    }
    
    public void noOrderExistsMessage() {
        io.print("");
        io.print("No order exists with that criteria!");
        io.print("");
    }
    
    public void displayExitMessage() {
        io.print("");
        io.print("Good bye!");
    }
    
    public void displayUnknownCommandMessage() {
        io.print("Unknown Command!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
