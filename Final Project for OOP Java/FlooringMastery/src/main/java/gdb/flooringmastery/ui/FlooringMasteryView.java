package gdb.flooringmastery.ui;

import gdb.flooringmastery.dto.Order;
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
    
    public int displayMainMenuGetSelection() {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("* <<Flooring Program>>");
        io.print("* 1. Search for Orders by Date");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Save Current Work");
        io.print("* 6. Exit");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("");
        
        int selection = 0;
        
        try {
            selection = io.readInt("Please make your slection from the menu above.", 1, 6);
        } catch (InputMismatchException e) {
            io.print("Bad input! Exiting program.");
        }
        
        return selection; 
    }
    
    //Option 1: Display orders by date input
    public LocalDate getOrderDateInputOption1() {
        io.print("*** Display Orders ***");
        String dateString = io.readString("Enter date for orders you wish to view (yyyy-MM-dd): "); 
        return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
    }
    
    public void displayOrders(List<Order> orderList) {
        io.print("");
        
        if (orderList.size() == 0) {
            io.print("No orders found! Returning to the main menu.");
            io.print("");
        } else {
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
    
    //Option 2: Add an Order
    public Order addOrder(int highestOrderNum) {
        int orderNumber = highestOrderNum + 1;
        String customerName = "";
        String orderDateString = "";
        LocalDate orderDateLD = LocalDate.now();
        String state = "";
        String productType = "";
        double area = 0;
        boolean areaValid = false;
        
        //Ensuring that these fields are filled in and aren't left blank
        do {
            customerName = io.readString("Enter customer name:");
        } while (customerName.equals(""));
        
        do {
            orderDateString = io.readString("Enter order date (yyyy-MM-dd):");
            orderDateLD = LocalDate.parse(orderDateString, DateTimeFormatter.ISO_DATE);
        } while (orderDateString.equals(""));
        
        do {
            state = io.readString("Enter US state of order location:");
        } while (state.equals(""));
        
        do {
            productType = io.readString("Enter material type of product:");
        } while (productType.equals(""));
        
        do {
            try {
                area = io.readDouble("Enter product area in sq ft:");
                areaValid = true;
            } catch (InputMismatchException e) {
                io.print("Bad input! Try again.");
            }
        } while (!areaValid);
                
        Order currentOrder = new Order(orderNumber);
        
        currentOrder.setCustomerName(customerName);
        currentOrder.setOrderDate(orderDateLD);
        currentOrder.setState(state);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);
        
        return currentOrder;
    }
    
    //Option 3: Edit an order
    
    //Option 4: Remove an order
    
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
