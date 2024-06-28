package SystemManagementInventory;

public class FragileItem extends InventoryItem {
    double weight;

    public FragileItem(String itemName, String category, double price, boolean breakable,
                       boolean perishable, String itemId, int quantity, double weight) {
        super(itemName, category, price, breakable, perishable, itemId, quantity);
        this.weight = weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public void displayDescription() {
        super.displayDescription();
        System.out.println("Weight: " + weight);
    }
}
