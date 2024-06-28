package SystemManagementInventory;

public class ElectronicsItem extends InventoryItem {
    private int warrantyYears;

    public ElectronicsItem(String itemName, String category, double price, boolean breakable,
                           boolean perishable, String itemId, int quantity, int warrantyYears) {
        super(itemName, category, price, breakable, perishable, itemId, quantity);
        this.warrantyYears = warrantyYears;
    }

    public void setWarrantyYears(int warrantyYears) {
        this.warrantyYears = warrantyYears;
    }

    public int getWarrantyYears() {
        return warrantyYears;
    }
}
