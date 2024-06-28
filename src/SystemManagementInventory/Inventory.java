package SystemManagementInventory;

public class Inventory extends AbstractItem {
    private String itemId;
    private int quantity;

    public Inventory(String itemName, String category, double price, boolean breakable, boolean perishable) {
        super(itemName, category, price, breakable, perishable);
    }

    @Override
    public double calculateValue() {
        return getItemPrice() * quantity;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
