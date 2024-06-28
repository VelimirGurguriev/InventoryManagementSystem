package SystemManagementInventory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InventoryManagementSystem {
    private List<InventoryItem> inventoryList;
    private BufferedReader reader;

    public InventoryManagementSystem() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        inventoryList = new ArrayList<>();
    }

    public void run() {
        int choice;
        do {
            choice = getUserInput();
            switch (choice) {
                case 1:
                    System.out.println("Add item option");
                    addItem();
                    break;
                case 2:
                    System.out.println("Remove item by ID");
                    break;
                case 3:
                    System.out.println("Display list of items");
                    break;
                case 4:
                    System.out.println("Categorize items");
                    break;
                case 5:
                    System.out.println("Place orders");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        } while (choice != 0);
        closeReader();
    }

    private int getUserInput() {
        int option = 0;
        boolean validInput = false;

        while (!validInput) {
            printMenu();
            try {
                System.out.print("Enter option: ");
                option = Integer.parseInt(reader.readLine());
                validInput = true;
            } catch (IOException exc) {
                exc.printStackTrace();
            } catch (NumberFormatException exc) {
                System.out.println("Please input a number.");
            }
        }

        return option;
    }

    private void closeReader() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing reader: " + e.getMessage());
        }
    }

    private void printMenu() {
        System.out.println("Inventory Management Menu:");
        System.out.println("1. Add item.");
        System.out.println("2. Remove item by ID.");
        System.out.println("3. Display list of items.");
        System.out.println("4. Categorize items.");
        System.out.println("5. Place orders.");
        System.out.println("0. Exit.");
    }

    private void addItem() {
        try {
            System.out.print("Enter type (Electronics/Grocery/Fragile): ");
            String itemType = reader.readLine().trim().toLowerCase();
            System.out.print("Enter Item ID: ");
            String itemId = reader.readLine();
            System.out.print("Enter Item Name: ");
            String itemName = reader.readLine();
            System.out.print("Enter Category: ");
            String category = reader.readLine();
            System.out.print("Enter Price: ");
            double price = Double.parseDouble(reader.readLine());
            System.out.print("Is it Breakable (true/false)? : ");
            boolean breakable = Boolean.parseBoolean(reader.readLine());
            System.out.print("Is it Perishable (true/false)? : ");
            boolean perishable = Boolean.parseBoolean(reader.readLine());
            System.out.print("Enter Quantity: ");
            int quantity = Integer.parseInt(reader.readLine());

            InventoryItem item;

            switch (itemType) {
                case "electronics":
                    System.out.print("Enter Warranty Years: ");
                    int warrantyYears = Integer.parseInt(reader.readLine());
                    item = new ElectronicsItem(itemName, category, price, breakable, perishable, itemId, quantity, warrantyYears);
                    break;
                case "grocery":
                    System.out.print("Enter Expiration Date (dd/mm/yyyy): ");
                    String expirationDate = reader.readLine();
                    item = new GroceryItem(itemName, category, price, breakable, perishable, itemId, quantity, expirationDate);
                    break;
                case "fragile":
                    System.out.print("Enter Weight in kilograms: ");
                    double weight = Double.parseDouble(reader.readLine());
                    item = new FragileItem(itemName, category, price, breakable, perishable, itemId, quantity, weight);
                    break;
                default:
                    System.out.println("Invalid item type.");
                    return;
            }

            inventoryList.add(item);
            System.out.println("Item added successfully.");
        } catch (IOException exc) {
            System.out.println("Error during input: " + exc.getMessage());
        } catch (NumberFormatException exc) {
            System.out.println("Invalid input format.");
        }
    }
}
