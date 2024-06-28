package SystemManagementInventory;

import ApplicationConsoleCommerce.PaymentMethod;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private double totalOrderAmount;
    private static int currentOrderCount = 1;
    private int orderId;
    private Map<InventoryItem, Integer> items;
    private PaymentMethod paymentMethod;

    public Order() {
        this.orderId = currentOrderCount++;
        this.items = new HashMap<>();
    }

    public void addItemToOrder(InventoryItem item, int quantity) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + quantity);
        } else {
            items.put(item, quantity);
        }
        totalOrderAmount += item.getItemPrice() * quantity;
    }

    public void processOrder(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        System.out.println("Order " + orderId + " was paid with " + paymentMethod.getPaymentType());
    }

    public void displayOrder() {
        System.out.println("Order ID: " + orderId);
        for (Map.Entry<InventoryItem, Integer> entry : items.entrySet()) {
            InventoryItem item = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(quantity + " of " + item.getItemName()
                    + " costing " + item.getItemPrice() + "LV. for each");
        }
        System.out.println("Total order amount: " + totalOrderAmount + "LV.");
    }

    public Map<InventoryItem, Integer> getItems() {
        return items;
    }

    public double getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(double totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }
}
