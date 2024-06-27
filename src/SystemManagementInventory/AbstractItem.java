package SystemManagementInventory;

public abstract class AbstractItem implements Item, Categorizable,
        Breakable, Perishable, Sellable {
    private String itemName;
    private String category;
    private double price;
    private boolean breakable;
    private boolean perishable;

    public AbstractItem(String itemName, String category, double price, boolean breakable, boolean perishable) {
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.breakable = breakable;
        this.perishable = perishable;
    }

    // Item interface method implementation
    @Override
    public void getDetails() {

    }

    @Override
    public void calculateValue() {

    }

    @Override
    public void displayDescription() {

    }

    // Categorizable interface method implementation
    @Override
    public void setItemCategory(String category) {

    }

    @Override
    public String getItemCategory() {
        return "";
    }

    // Breakable interface method implementation
    @Override
    public boolean isBreakable() {
        return false;
    }

    @Override
    public void handleBreakage() {

    }

    // Perishable interface method implementation
    @Override
    public boolean isPerishable() {
        return false;
    }

    @Override
    public void handleExpiration() {

    }


    // Sellable interface method implementation
    @Override
    public void setItemPrice(double price) {

    }

    @Override
    public double getItemPrice() {
        return 0;
    }
}
