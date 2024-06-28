package SystemManagementInventory;

public interface Item {
    void setItemName(String name);
    String getItemName();
    double calculateValue();
    void displayDescription();
}
