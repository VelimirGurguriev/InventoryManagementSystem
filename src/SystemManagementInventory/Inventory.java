package SystemManagementInventory;

public class Inventory extends AbstractItem {
    
    public Inventory(String itemName, String category, double price, boolean breakable, boolean perishable) {
        super(itemName, category, price, breakable, perishable);
    }

    @Override
    public double calculateValue() {
        return 0;
    }
}
