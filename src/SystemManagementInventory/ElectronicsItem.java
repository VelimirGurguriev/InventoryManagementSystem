package SystemManagementInventory;

public class ElectronicsItem extends InventoryItem {

    public ElectronicsItem(String itemName, String category, double price, boolean breakable,
                           boolean perishable, String itemId, int quantity) {
        super(itemName, category, price, breakable, perishable, itemId, quantity);
    }
}
