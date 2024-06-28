package SystemManagementInventory;

public class InventoryItem extends AbstractItem {
    private String itemId;
    private int quantity;

    public InventoryItem() {
    }

    public InventoryItem(String itemName, String category, double price, boolean breakable, boolean perishable,
                         String itemId, int quantity) {
        super(itemName, category, price, breakable, perishable);
        this.itemId = itemId;
        this.quantity = quantity;
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
