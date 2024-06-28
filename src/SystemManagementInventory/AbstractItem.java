package SystemManagementInventory;

public abstract class AbstractItem implements Item, Categorizable,
        Breakable, Perishable, Sellable {
    private String itemName;
    private String category;
    private double price;
    private boolean breakable;
    private boolean perishable;

    public AbstractItem() {
    }

    public AbstractItem(String itemName, String category, double price, boolean breakable, boolean perishable) {
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.breakable = breakable;
        this.perishable = perishable;
    }

    // Item interface method implementation
    @Override
    public void setItemName(String name) {
        this.itemName = name;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public abstract double calculateValue();

    @Override
    public void displayDescription() {
        System.out.println("Item: " + itemName + "\nCosts " + price + "LV.\nCategory: " + category +
                ".\nBreakable: " + breakable + ".\nPerishable: " + perishable + ".");
    }

    // Categorizable interface method implementation
    @Override
    public void setItemCategory(String category) {
        this.category = category;
    }

    @Override
    public String getItemCategory() {
        return category;
    }

    // Breakable interface method implementation
    @Override
    public void setToBreakable() {
        this.breakable = true;
    }

    @Override
    public boolean checkBreakable() {
        return breakable;
    }

    @Override
    public void handleBreakage() {
        // to do
    }

    // Perishable interface method implementation
    @Override
    public void setToPerishable() {
        this.perishable = true;
    }

    @Override
    public boolean checkPerishable() {
        return perishable;
    }

    @Override
    public void handleExpiration() {
        // to do
    }

    // Sellable interface method implementation
    @Override
    public void setItemPrice(double price) {
        this.price = price;
    }

    @Override
    public double getItemPrice() {
        return price;
    }
}
