package gdb.flooringmastery.ui;

import gdb.flooringmastery.dto.Order;
import gdb.flooringmastery.dto.Product;
import gdb.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        io.print("");
        
        int selection = 0;
        
        try {
            selection = io.readInt("Choose program mode from number options above.", 1, 2);
        } catch (InputMismatchException e) {
            io.print("Bad input! Exiting program.");
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
        
        int selection = 0;
        io.print("");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("* --- " + modeLabel + " ---");
        io.print("* <<Flooring Program>>");
        io.print("* 1. Show all Orders");
        io.print("* 2. Search for Orders by Date");
        io.print("* 3. Add an Order");
        io.print("* 4. Edit an Order");
        io.print("* 5. Remove an Order");
        
        if (mode == 1) {
            io.print("* 6. Save Current Work");
            io.print("* 7. Exit");
            io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
            io.print("");
            
            try {
                selection = io.readInt("Please enter a number option from the menu above.", 1, 7);
            } catch (InputMismatchException e) {
                io.print("Bad input! Exiting program.");
            }
        } else if (mode == 2) {
            io.print("* 6. Exit");
            io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
            io.print("");
            
            try {
                selection = io.readInt("Please enter a number option from the menu above.", 1, 6);
            } catch (InputMismatchException e) {
                io.print("Bad input! Exiting program.");
            }
        }
    
        return selection; 
    }
    
    //Option 2: Display orders by date input
    public LocalDate getOrderDateInputOption2() {
        io.print("*** Display Orders ***");
        String dateString = io.readString("Enter date for orders you wish to view (yyyy-MM-dd): "); 
        return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
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
        LocalDate orderDateLD = LocalDate.now();
        String state = null;
        String productType = null;
        double area = 0;
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
            orderDateString = io.readString("Enter order date (yyyy-MM-dd):");
            orderDateLD = LocalDate.parse(orderDateString, DateTimeFormatter.ISO_DATE);
        } while (orderDateString.equals(""));
        
        //Get state of order (for tax purposes)
        do {
            io.print("");
            io.print("Options for US states:");
            
            //Print out each type of product so the user can see what the options are
            for (Tax t : taxList) {
                io.print(t.getState());
            }
            
            state = io.readString("Enter US state of order location (XX):");
        } while (state.equals("") || state.length() != 2);
        
        //Get material type for product
        do {
            io.print("");
            io.print("Options for product materials:");
            
            //Print out each type of product so the user can see what the options are
            for (Product p : productList) {
                io.print(p.getProductType());
            }
            
            productType = io.readString("Enter material type:");
        } while (productType.equals(""));
        
        //Get product area
        do {
            io.print("");
            try {
                area = io.readDouble("Enter product area in sq ft:");
                areaValid = true;
            } catch (InputMismatchException e) {
                io.print("Bad input! Try again.");
            }
        } while (!areaValid);
         
        //Confirm with user to make commitment to add order
        //Show what the user put in and have user input y/n/yes/no
        io.print("");
        io.print("New Order Details You Gave:");
        io.print("Customer name: " + customerName);
        io.print("Order Date: " + orderDateLD);
        io.print("State Location: " + state);
        io.print("Product Material: " + productType);
        io.print("Area: " + area + " sq ft");
        
        do {
            io.print("");
            confirm = io.readString("Commit to add order? (y/n):");
        } while (confirm.equals("") || (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n") && !confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("no")));
        
        Order currentOrder;
        
        if (confirm.equals("y") || confirm.equals("yes")) {
            //Make the new order with all fields put in
            currentOrder = new Order(orderNumber);
        
            currentOrder.setCustomerName(customerName);
            currentOrder.setOrderDate(orderDateLD);
            currentOrder.setState(state);
            currentOrder.setProductType(productType);
            currentOrder.setArea(area);
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
        String dateString = io.readString("Enter date for the order you would like to edit (yyyy-MM-dd): "); 
        return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
    }
    
    public String getCustomerNameInputOption4() {
        io.print("");
        return io.readString("Enter customer name for order you would like to edit: "); 
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
        LocalDate orderDateLD = LocalDate.now();
        String state = "";
        String productType = "";
        double area = 0;
        boolean areaValid = false;
        
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
            editedOrder.setOrderDate(LocalDate.parse(orderDateString, DateTimeFormatter.ISO_DATE));
        }
        
        //US State (for tax purposes)
        io.print("");
        io.print("Options for US states:");
        for (Tax t : taxList) {
            io.print(t.getState());
        }
        state = io.readString("Enter state location (" + orderToEdit.getState() + "): ");
        if (!state.equals("")) {
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
            editedOrder.setProductType(productType);
        }
        
        //Area of product
        do {
            io.print("");
            try {
                area = io.readDouble("Enter product area in sq ft (" + orderToEdit.getArea() + "): ");
                areaValid = true;
            } catch (InputMismatchException e) {
                io.print("Bad input! Try again.");
            }
        } while (!areaValid);
        if (area != 0) {
            editedOrder.setArea(area);
        }
        
        io.print("");
        io.print("Order successfully edited.");
        
        return editedOrder;
    }
    
    //Option 5: Remove an order
    public LocalDate getOrderDateInputOption5() {
        io.print("*** Remove an Order ***");
        String dateString = io.readString("Enter date for the order you would like to remove (yyyy-MM-dd): "); 
        return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
    }
    
    public String getCustomerNameInputOption5() {
        io.print("");
        return io.readString("Enter customer name for order you would like to remove: "); 
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
    
    public void savedCurrentWorkMessage() {
        io.print("");
        io.print("Data successfully saved to the file.");
        io.print("");
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
