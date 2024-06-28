package SystemManagementInventory;

public class GroceryItem extends InventoryItem {
    private String expirationDate;

    public GroceryItem(String itemName, String category, double price, boolean breakable,
                       boolean perishable, String itemId, int quantity, String expirationDate) {
        super(itemName, category, price, breakable, perishable, itemId, quantity);
        this.expirationDate = expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
}
