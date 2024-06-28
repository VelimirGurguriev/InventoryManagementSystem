package SystemManagementInventory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InventoryManagementSystem {
    private BufferedReader reader;

    public InventoryManagementSystem() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {
        int choice;
        do {
            choice = getUserInput();
            switch (choice) {
                case 1:
                    System.out.println("Add item option");
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
}
