package SystemManagementInventory;

import ApplicationConsoleCommerce.CreditCardPayment;
import ApplicationConsoleCommerce.PayPalPayment;
import ApplicationConsoleCommerce.PaymentMethod;
import ApplicationConsoleCommerce.PaymentProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryManagementSystem {
    private List<InventoryItem> inventoryList;
    private BufferedReader reader;
    private List<Order> orderList;
    private PaymentProcessor paymentProcessor;
    private Order currentOrder;

    public InventoryManagementSystem() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        inventoryList = new ArrayList<>();
        orderList = new ArrayList<>();
        paymentProcessor = new PaymentProcessor();
    }

    // method that calls the menu and processes the user input
    public void run() {
        int choice;
        do {
            choice = getUserInput();
            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeById();
                    break;
                case 3:
                    displayList();
                    break;
                case 4:
                    categorizeItems();
                    break;
                case 5:
                    addItemToCart(currentOrder);
                    break;
                case 6:
                    viewCart(currentOrder);
                    break;
                case 7:
                    processOrder(currentOrder);
                    currentOrder = new Order();
                    break;
                case 8:
                    loadFromFileSerialize();
                    break;
                case 0:
                    saveToFileSerialize();
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
        System.out.println("5. Add item to cart.");
        System.out.println("6. View cart.");
        System.out.println("7. Process order.");
        System.out.println("8. Load items from database.");
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

    public void displayList() {
        if (inventoryList.isEmpty()) {
            System.out.println("No items in the inventory.");
        } else {
            System.out.println("Inventory Items:");
            System.out.println("--------------------------");
            for (InventoryItem item : inventoryList) {
                item.displayDescription();
                System.out.println("--------------------------");
            }
        }
    }

    public void removeById() {
        if (inventoryList.isEmpty()) {
            System.out.println("Inventory is empty. Cannot remove items.");
            return;
        }

        System.out.println("Enter remove ID: ");
        String removeID;
        try {
            removeID = reader.readLine().trim();
        } catch (IOException exc) {
            System.out.println("Error reading ID: " + exc.getMessage());
            return;
        }

        boolean isFound = false;
        for (int i = 0; i < inventoryList.size(); i++) {
            InventoryItem currentItem = inventoryList.get(i);
            if (currentItem.getItemId().equals(removeID)) {
                inventoryList.remove(i);
                System.out.println("Item " + removeID + " successfully removed.");
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            System.out.println("Item " + removeID + " was not found in inventory.");
        }
    }

    public void categorizeItems() {
        if (inventoryList.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        Map<String, List<InventoryItem>> categoryMap = new HashMap<>();

        for (InventoryItem currentItem : inventoryList) {
            String category = currentItem.getItemCategory();
            if (!categoryMap.containsKey(category)) {
                categoryMap.put(category, new ArrayList<>());
            }
            categoryMap.get(category).add(currentItem);
        }

        System.out.println("Catagorized items:");
        for (Map.Entry<String, List<InventoryItem>> entry : categoryMap.entrySet()) {
            String category = entry.getKey();
            List<InventoryItem> items = entry.getValue();
            System.out.println("==========================");
            System.out.println("Category: " + category + "\n");
            for (InventoryItem currentItem : items) {
                currentItem.displayDescription();
                System.out.println("--------------------------");
            }
        }
    }

    public void saveToFileSerialize() {
        String fileName = "database.txt";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(inventoryList);
            oos.writeObject(orderList);
            System.out.println("Inventory data successfully saved to database.");
        } catch (IOException exc) {
            System.out.println("Error during saving: " + exc.getMessage());
        }
    }

    public void loadFromFileSerialize() {
        String fileName = "database.txt";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (!(obj instanceof List)) {
                System.out.println("File does not contain a list.");
            } else {
                inventoryList = (List<InventoryItem>) obj;
                if (inventoryList.isEmpty()) {
                    System.out.println("File loaded, but contains NO list elements.");
                } else {
                    System.out.println("Inventory data successfully loaded from database.");
                }
            }
        } catch (IOException | ClassNotFoundException exc) {
            System.out.println("Error loading data from file: " + exc.getMessage());
        }
    }

    // Payment processing methods
    private void addItemToCart(Order order) {
        try {
            System.out.print("Enter Item ID to add in cart: ");
            String itemID = reader.readLine();
            InventoryItem orderedItem = null;
            for (InventoryItem currentItem : inventoryList) {
                if (currentItem.getItemId().equals(itemID)) {
                    orderedItem = currentItem;
                    break;
                }
            }

            if (orderedItem != null) {
                System.out.print("Enter quantity to order: ");
                int quantity = Integer.parseInt(reader.readLine());
                if (0 < quantity && quantity <= orderedItem.getQuantity()) {
                    order.addItemToOrder(orderedItem, quantity);
                    System.out.println("Successfully added to cart: " + quantity + " x "
                        + orderedItem.getItemName());
                } else {
                    System.out.println("Invalid quantity. Available in stock: " + orderedItem.getQuantity());
                }
            } else {
                System.out.println("Item with ID: " + itemID + " was not found.");
            }
        } catch (IOException | NumberFormatException exc) {
            System.out.println("Error reading input: " + exc.getMessage());
        }
    }

    private void processOrder(Order order) {
        try {
            if (order.getItems().isEmpty()) {
                System.out.println("Empty cart. Please first add some items.");
                return;
            }
        } catch (NullPointerException exc) {
            System.out.println("Empty cart. Please first add some items.");
            return;
        }

        System.out.println("Order total amount: " + order.getTotalOrderAmount() + "LV.");
        try {
            System.out.print("Enter payment method (card/paypal): ");
            String paymentMethodType = reader.readLine().trim().toLowerCase();
            System.out.print("Enter payment amount: ");
            double paymentAmount = Double.parseDouble(reader.readLine());

            PaymentMethod paymentMethod;
            if (paymentMethodType.equals("credit")) {
                System.out.print("Enter card number: ");
                String cardNumber = reader.readLine();
                System.out.print("Enter card holder name: ");
                String cardHolder = reader.readLine();
                System.out.print("Enter expiration date (MM/YY): ");
                String expirationDate = reader.readLine();
                System.out.print("Enter CVV: ");
                String cvv = reader.readLine();
                paymentMethod = new CreditCardPayment(cardNumber, cardHolder, expirationDate, cvv);
            } else if (paymentMethodType.equals("paypal")) {
                System.out.print("Enter PayPal email: ");
                String email = reader.readLine();
                System.out.print("Enter PayPal password: ");
                String password = reader.readLine();
                paymentMethod = new PayPalPayment(email, password);
            } else {
                System.out.println("Invalid payment method.");
                return;
            }

            if (paymentProcessor.processPayment(paymentMethod, paymentAmount)) {
                order.processOrder(paymentMethod);
                orderList.add(order);
                updateInventoryQuantities(order);
                System.out.println("Order processed successfully.");
            } else {
                System.out.println("Payment failed. Order not processed.");
            }
        } catch (IOException | NumberFormatException exc) {
            System.out.println("Error reading input: " + exc.getMessage());
        }
    }

    private void updateInventoryQuantities(Order order) {
        for (Map.Entry<InventoryItem, Integer> entry : order.getItems().entrySet()) {
            InventoryItem item = entry.getKey();
            int quantityOrdered = entry.getValue();
            item.setQuantity(item.getQuantity() - quantityOrdered);
        }
    }

    private void viewCart(Order order) {
        try {
            System.out.println("Current Cart:");
            order.displayOrder();
        } catch (NullPointerException exc) {
            System.out.println("Cart is currently empty.");
        }

    }
}
