package SystemManagementInventory;

public class FragileItem extends InventoryItem {

    public FragileItem(String itemName, String category, double price, boolean breakable,
                       boolean perishable, String itemId, int quantity) {
        super(itemName, category, price, breakable, perishable, itemId, quantity);
    }
}
