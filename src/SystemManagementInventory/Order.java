package SystemManagementInventory;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<InventoryItem> items;
    private double totalOrderAmount;

    public Order() {
        items = new ArrayList<>();
        totalOrderAmount = 0.0;
    }

    public void addItemToOrder(InventoryItem item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            items.add(item);
        }
        totalOrderAmount += item.getItemPrice() * quantity;
    }

    public void processOrder(Payment payment) {
        if (payment.getAmount() >= totalOrderAmount) {
            System.out.println("Order is processed. Total amount is: " + totalOrderAmount + "LV.");
        } else {
            System.out.println("Not enough money!. Order failed to process.");
        }
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public double getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(double totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }
}
