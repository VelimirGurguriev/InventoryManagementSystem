package SystemManagementInventory;

public class GroceryItem extends InventoryItem {

    public GroceryItem(String itemName, String category, double price, boolean breakable,
                       boolean perishable, String itemId, int quantity) {
        super(itemName, category, price, breakable, perishable, itemId, quantity);
    }
}
